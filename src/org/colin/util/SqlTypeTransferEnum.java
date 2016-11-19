package org.colin.util;

/**
 * @author Colin
 *
 */
public enum SqlTypeTransferEnum {
	CHAR("char", "String"),
	VARCHAR("varchar", "String"),
	LONGVARCHAR("longvarchar", "String"),
	NVARCHAR("nvarchar", "String"),
	NUMERIC("numeric", "BigDecimal"),
	DECIMAL("decimal", "BigDecimal"),
	BIT("bit", "Boolean"),
	TINYINT("tinyint", "Integer"),
	SMALLINT("smallint", "Integer"),
	INT("int", "Integer"),
	BIGINT("bigint", "Long"),
	BIGINT_IDENTITY("bigint identity", "Long"),
	REAL("real", "String"),
	FLOAT("float", "BigDecimal"),
	DOUBLE("double", "BigDecimal"),
	BINARY("binary", "byte[]"),
	VARBINARY("varbinary", "byte[]"),
	LONGVARBINARY("longvarbinary", "byte[]"),
	DATETIME("datetime", "Date"),
	DATE("date", "Date"),
	TIME("time", "Date");
	
	private String sqlType;
	private String javaType;	
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	private SqlTypeTransferEnum(String sqlType,String javaType){
		this.sqlType = sqlType;
		this.javaType = javaType;
	}
	/**
	 * 
	 * @param sqlType
	 * @return
	 */
	public static String getJavaTypeBySqlType(String sqlType){
		
		for(SqlTypeTransferEnum a:SqlTypeTransferEnum.values()){
			if(a.sqlType.equals(sqlType)){
				return a.javaType;
			}
		}
		
		return null;
	}

}
