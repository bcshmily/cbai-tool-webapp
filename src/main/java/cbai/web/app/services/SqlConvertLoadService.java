package cbai.web.app.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cbai.util.db.define.TableBean;
import cbai.util.sqlconvert.NormalSqlConverter;
import cbai.util.sqlconvert.SqlConverterAbstract;
import cbai.web.app.toolbox.services.ISqlConvertLoadService;

@Service
public class SqlConvertLoadService implements ISqlConvertLoadService {

    @Override
    public SqlConverterAbstract execute(String projectId, List<TableBean> tableList) {
        return new NormalSqlConverter(tableList) {
            @Override
            public Map<String, String> prepareLines(List<String> lines) {
                return null;
            }
        };
    }

}
