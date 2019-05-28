package cn.edu.gzmu.model.constant;

/**
* ${class_name} Repository
*
* @author echo
* @version ${now_version}
* @date ${.now?datetime}
*/
@SuppressWarnings("all")
public interface LessonResource {
<#list tables as table>

    public static final String ${table.tableName?upper_case} = "/${table.classNameNew}/search";
</#list>

}
