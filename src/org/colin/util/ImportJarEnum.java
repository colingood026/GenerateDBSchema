package org.colin.util;
/**
 * @author Colin
 *
 */
public enum ImportJarEnum {
	Annotation_Mybatis_Alias("import org.apache.ibatis.type.Alias;"),
	Annotation_Sprig_Repository("import org.springframework.stereotype.Repository;"),
	Annotation_Mybatis_Param("import org.apache.ibatis.annotations.Param;"),
	//
	BigDecimal("import java.math.BigDecimal;"),
	Date("import java.util.Date;");
	private String importName;
	private ImportJarEnum(String importNm){
		this.importName = importNm;
	}
	public String getImportName() {
		return importName;
	}
	public void setImportName(String importName) {
		this.importName = importName;
	}
	/**
	 * 
	 * @param typeNm
	 * @return
	 */
	public static String getImportStrByTypeName(String typeNm){
		for(ImportJarEnum a:ImportJarEnum.values()){
			if(a.name().equals(typeNm)){
				return a.importName;
			}
		}		
		return null;
	}
}
