package org.colin.generate.mybatis;
import java.util.List;
import java.util.Set;

import org.colin.util.ImportJarEnum;
import org.colin.util.MethodUtils;

/**
 * 
 */

/**
 * @author Colin
 *
 */
public class GenerateMybatisJavaBean {

	

	
	private GenerateMybatisJavaBean(){
		
	}
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
		sb.append("package "+packageNm+".model."+classNm).append(MethodUtils.CHANGE_LINE);
		for(String importJar:importJars){			
			sb.append(importJar).append(MethodUtils.CHANGE_LINE);
		}
		sb.append(ImportJarEnum.Annotation_Mybatis_Alias.getImportName()).append(MethodUtils.CHANGE_LINE);
		sb.append(MethodUtils.CHANGE_LINE);
		sb.append("@Alias(\""+classNm+"\")").append(MethodUtils.CHANGE_LINE);
		sb.append("public class "+classNm+" implements Serializable{").append(MethodUtils.CHANGE_LINE);
		sb.append(MethodUtils.CHANGE_LINE);
		sb.append(MethodUtils.CAPS+"private static final long serialVersionUID = 1L;").append(MethodUtils.CHANGE_LINE);
		sb.append(MethodUtils.CHANGE_LINE);
		// create field
		for(String[] field:fields){
			String name = MethodUtils.removeSplitForField(field[0]);
			String type = field[1];
			sb.append(createField(name,type)).append(MethodUtils.CHANGE_LINE);
		}		
		sb.append(MethodUtils.CHANGE_LINE);
		// create get
		for(String[] field:fields){
			String name = MethodUtils.removeSplitForField(field[0]);
			String type = field[1];
			sb.append(createGet(name,type)).append(MethodUtils.CHANGE_LINE);
		}
		sb.append(MethodUtils.CHANGE_LINE);
		// create set
		for(String[] field:fields){
			String name = MethodUtils.removeSplitForField(field[0]);
			String type = field[1];
			sb.append(createSet(name,type)).append(MethodUtils.CHANGE_LINE);
		}
		sb.append(MethodUtils.CHANGE_LINE);
		sb.append("}");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param fieldNm
	 * @param type
	 * @return
	 */
	private static String createField(String fieldNm,String type){
		return MethodUtils.CAPS+"private "+type+" "+fieldNm+";";
	}
	/**
	 * 
	 * @param fieldNm
	 * @param type
	 * @return
	 */
	private static String createGet(String fieldNm,String type){
//		public String getJavaType() {
//			return javaType;
//		}
		String firstChar = fieldNm.substring(0, 1);
		String get = "get"+firstChar.toUpperCase()+fieldNm.substring(1);
		return MethodUtils.CAPS+"public "+type+" "+get+"(){ return "+fieldNm+";}";
	}
	/**
	 * 
	 * @param fieldNm
	 * @param type
	 * @return
	 */
	private static String createSet(String fieldNm,String type){
//		public void setJavaType(String javaType) {
//			this.javaType = javaType;
//		}
		String firstChar = fieldNm.substring(0, 1);
		String set = "set"+firstChar.toUpperCase()+fieldNm.substring(1);
		return MethodUtils.CAPS+"public void "+set+"("+type+" "+fieldNm+"){"+MethodUtils.CHANGE_LINE+MethodUtils.DOUBLE_CAPS+
										"this."+fieldNm+" = "+fieldNm+";"+MethodUtils.CHANGE_LINE+MethodUtils.CAPS+"}";
		
	}
	

}
