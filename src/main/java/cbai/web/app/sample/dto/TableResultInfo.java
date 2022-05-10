package cbai.web.app.toolbox.dto;

import java.util.List;

import cbai.util.db.define.TableBean;
import lombok.Data;

@Data
public class TableResultInfo {
    /** 结果 */
    private boolean success = false;

    /** 项目ID */
    private String projectId;
    /** 项目名 */
    private String projectName;
    /** 数据库类型 */
    private String dbType;
    /** 数据表情报列表 */
    private List<TableBean> tableList;
    private String ver;

}
