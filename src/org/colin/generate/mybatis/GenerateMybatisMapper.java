package org.colin.generate.mybatis;
import java.util.List;
import java.util.Set;

import org.colin.util.ImportJarEnum;
import org.colin.util.MethodUtils;

public class GenerateMybatisMapper {
	/**
	 * 
	 * @param importJars
	 * @param fields
	 * @param classNm
	 */
	public static String build(String packageNm,
			                   Set<String> importJars, 
							   List<String[]> fields, 
							   String classNm){
		
		StringBuffer sb = new StringBuffer();
		sb.append("package "+packageNm+".mapper."+classNm).append(MethodUtils.CHANGE_LINE);
		sb.append(ImportJarEnum.Annotation_Sprig_Repository.getImportName()).append(MethodUtils.CHANGE_LINE);
		sb.append(ImportJarEnum.Annotation_Mybatis_Param.getImportName()).append(MethodUtils.CHANGE_LINE);
		sb.append("@Repository").append(MethodUtils.CHANGE_LINE);
		sb.append("public interface "+classNm+"Mapper{").append(MethodUtils.CHANGE_LINE);
		

		sb.append(MethodUtils.CAPS+"public List<"+classNm+"> getAll();").append(MethodUtils.CHANGE_LINE);;
		
		
		sb.append("}");
		
		return sb.toString();
	}
}
