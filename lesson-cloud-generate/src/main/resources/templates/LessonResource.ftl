package cn.edu.gzmu.model.constant;

@SuppressWarnings("all")
public interface LessonResource {
<#list tables as table>

    public static final String ${table.tableName?upper_case} = "/${table.classNameNew}/search";
</#list>

}
