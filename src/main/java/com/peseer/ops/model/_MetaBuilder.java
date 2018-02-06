package com.peseer.ops.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Hashtable;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.generator.ColumnMeta;
import com.jfinal.plugin.activerecord.generator.MetaBuilder;
import com.jfinal.plugin.activerecord.generator.TableMeta;

public class _MetaBuilder extends MetaBuilder{

	public _MetaBuilder(DataSource dataSource) {
		super(dataSource);
	}
	
	/**
	 * 文档参考：
	 * http://dev.mysql.com/doc/connector-j/en/connector-j-reference-type-conversions.html
	 * 
	 * JDBC 与时间有关类型转换规则，mysql 类型到 java 类型如下对应关系：
	 * DATE				java.sql.Date
	 * DATETIME			java.sql.Timestamp
	 * TIMESTAMP[(M)]	java.sql.Timestamp
	 * TIME				java.sql.Time
	 * 
	 * 对数据库的 DATE、DATETIME、TIMESTAMP、TIME 四种类型注入 new java.util.Date()对象保存到库以后可以达到“秒精度”
	 * 为了便捷性，getter、setter 方法中对上述四种字段类型采用 java.util.Date，可通过定制 TypeMapping 改变此映射规则
	 */
	@Override
	protected void buildColumnMetas(TableMeta tableMeta) throws SQLException {
		String sql = dialect.forTableBuilderDoBuild(tableMeta.name);
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		Hashtable<String, String> remarkCache = new Hashtable<>();
		ResultSet columnSet = dbMeta.getColumns(null, "%", tableMeta.name, "%");
		
		if (null != columnSet) {
			while (columnSet.next()) {
				//列名
				String columnName = columnSet.getString("COLUMN_NAME");
				//备注
				String columnComment = columnSet.getString("REMARKS");
				remarkCache.put(columnName, columnComment);
			}
		}
		
		for (int i=1; i<=rsmd.getColumnCount(); i++) {
			ColumnMeta cm = new ColumnMeta();
			cm.name = rsmd.getColumnName(i);

			String colClassName = rsmd.getColumnClassName(i);
			String typeStr = typeMapping.getType(colClassName);
			if (typeStr != null) {
				cm.javaType = typeStr;
			}
			else {
				int type = rsmd.getColumnType(i);
				if (type == Types.BINARY || type == Types.VARBINARY || type == Types.BLOB) {
					cm.javaType = "byte[]";
				}
				else if (type == Types.CLOB || type == Types.NCLOB) {
					cm.javaType = "java.lang.String";
				}
				else {
					cm.javaType = "java.lang.String";
				}
			}
			cm.remarks = remarkCache.containsKey(cm.name) ? remarkCache.get(cm.name) : StringUtils.EMPTY;
			// 构造字段对应的属性名 attrName
			cm.attrName = buildAttrName(cm.name);
			
			tableMeta.columnMetas.add(cm);
		}
		
		rs.close();
		stm.close();
	}

}
