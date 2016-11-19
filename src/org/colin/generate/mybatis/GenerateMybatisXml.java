package org.colin.generate.mybatis;
import java.util.List;

import org.colin.util.MethodUtils;

public class GenerateMybatisXml {

	/**
	 * 
	 * @param fields
	 * @param classNm
	 * @param tableNm
	 */
	public static String build(String packageNm,
			                   List<String[]> fields, 
							   String classNm,
							   String tableNm){		
		
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>").append(MethodUtils.CHANGE_LINE);;
		sb.append("<!DOCTYPE mapper PUBLIC '-//mybatis.org//DTD Mapper 3.0//EN'"
				+ " 'http://mybatis.org/dtd/mybatis-3-mapper.dtd'>").append(MethodUtils.CHANGE_LINE);
		sb.append("<mapper namespace='"+packageNm+".mapper."+classNm+"'>").append(MethodUtils.CHANGE_LINE);
		
		sb.append(MethodUtils.CAPS+"<resultMap type='"+classNm+"' id='"+classNm+"'>")
															.append(MethodUtils.CHANGE_LINE);
		// resultMap
        for(String[] field:fields){
        	String fieldNm = MethodUtils.removeSplitForField(field[0]);
        	String columnNm = field[0].toUpperCase();
        	if(fieldNm.equals("id")){
        		sb.append(MethodUtils.DOUBLE_CAPS+"<id property='"+fieldNm+"' column='"+columnNm+"'/>")
        																.append(MethodUtils.CHANGE_LINE);
        	}else{
        		sb.append(MethodUtils.DOUBLE_CAPS+"<result property='"+fieldNm+"' column='"+columnNm+"'/>")
        																.append(MethodUtils.CHANGE_LINE);
        	}        	
        }		
		sb.append(MethodUtils.CAPS+"</resultMap>").append(MethodUtils.CHANGE_LINE);
		// select
		sb.append(MethodUtils.CAPS+"<select id='getAll' resultMap='"+classNm+"'>").append(MethodUtils.CHANGE_LINE);
		sb.append(MethodUtils.DOUBLE_CAPS+"SELECT * FROM "+tableNm).append(MethodUtils.CHANGE_LINE);
        sb.append(MethodUtils.CAPS+"</select>").append(MethodUtils.CHANGE_LINE);
        //
        sb.append("</mapper>");
        
        return sb.toString();
		
	}
}
