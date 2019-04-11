<#list columns as column>
<#if column.columnComment!="">
rest.description.${column.tableName}.${column.columnName}=${column.columnComment}
</#if>
</#list>