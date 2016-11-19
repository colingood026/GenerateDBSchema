package org.colin.util;
/**
 * @author Colin
 *
 */
public enum DriverClassEnum {
	MSSQL("msSql","com.microsoft.sqlserver.jdbc.SQLServerDriver"),
	MYSQL("mySql","");
	
	private String dbNm;
	private String classNm;
	
	private DriverClassEnum(String dbNm, String classNm){
		this.dbNm = dbNm;
		this.classNm = classNm;
	}

	public String getDbNm() {
		return dbNm;
	}

	public void setDbNm(String dbNm) {
		this.dbNm = dbNm;
	}

	public String getClassNm() {
		return classNm;
	}

	public void setClassNm(String classNm) {
		this.classNm = classNm;
	}
	/**
	 * 
	 * @param dbNm
	 * @return
	 */
	public static String getClassNmByDbNm(String dbNm){
		for(DriverClassEnum a:DriverClassEnum.values()){
			if(a.dbNm.equals(dbNm)){
				return a.classNm;
			}
		}
		return null;
	}
}
