package cbai.web.app.toolbox.services;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cbai.util.StringUtils;
import cbai.util.db.define.TableBean;
import cbai.util.sqlconvert.NormalSqlConverter;
import cbai.util.sqlconvert.SqlConverterAbstract;
import cbai.web.app.common.services.TableBeanService;
import cbai.web.app.toolbox.dto.TableResultInfo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SqlConvertService {

    private Map<String, SqlConverterAbstract> holder = Collections.synchronizedMap(new HashMap<String, SqlConverterAbstract>());

    @Autowired
    private TableBeanService tableBeanService;

    public SqlConverterAbstract loadSqlConverter(String projectId) {
        if (StringUtils.isEmpty(projectId)) {
            throw new IllegalArgumentException("projectId can't ben null");
        }
        if (holder.containsKey(projectId)) {
            return holder.get(projectId);
        }
        SqlConverterAbstract sqlConverter = null;
        TableResultInfo tableResult = tableBeanService.loadTableInfos(projectId);
        if (!tableResult.isSuccess()) {
            throw new RuntimeException("can't find dbinfo with project:" + projectId);
        }
        sqlConverter = getConverterByProjectId(projectId, tableResult.getTableList());
        holder.put(projectId, sqlConverter);
        return sqlConverter;
    }

    private SqlConverterAbstract getConverterByProjectId(String projectId, List<TableBean> tableList) {
        SqlConverterAbstract mySqlConverter = null;
        if (projectId.length() > 1) {
            String implClassName = projectId.substring(0, 1).toUpperCase() + projectId.substring(1);
            implClassName = SqlConvertService.class.getPackage().getName() + ".impl." + implClassName + "SqlConvert";
            try {
                log.debug("查找该项目SqlConvert实装：" + implClassName);
                Class<?> convertClass = Class.forName(implClassName);
                Constructor<?> ct = convertClass.getConstructor(List.class);
                mySqlConverter = (SqlConverterAbstract) ct.newInstance(tableList);
            } catch (Exception e) {
                log.warn("未找到该项目实装：" + implClassName);
            }
        }
        if (mySqlConverter == null) {
            mySqlConverter = new NormalSqlConverter(tableList) {
                @Override
                public Map<String, String> prepareLines(List<String> lines) {
                    return null;
                }
            };
        }
        return mySqlConverter;
    }
}
