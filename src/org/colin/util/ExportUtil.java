package org.colin.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 
 */

/**
 * @author Colin
 *
 */
public class ExportUtil {
	

	/**
	 * 如果資料夾名稱重複，會再加上時間做區隔
	 * @param location
	 * @return
	 */
	public static String createDirectory(String location){
		
		try {
			File dbDir = new File(location);
			if(dbDir.exists()){				
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyymmddHHmmss");				
				location = location+"_"+sf.format(date);
				dbDir = new File(location);
			}
			dbDir.mkdir();
			File modelDir = new File(location+"/model");
			modelDir.mkdir();
			File mapperDir = new File(location+"/mapper");
			mapperDir.mkdir();
			File xmlDir = new File(location+"/xml");
			xmlDir.mkdir();
		} catch (Exception e) {
			e.printStackTrace();
			location = null;
		}
		return location;
	}
	/**
	 * 
	 * @param location
	 * @param classNm
	 * @param content
	 */
	public static void exportBean(String location,String classNm,String content){
		BufferedWriter bw = null;		
		FileWriter fw = null;
		try {
			File file = new File(location+"/model/"+classNm+".java");
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}finally{
			close(bw,fw);
		}
	}
	/**
	 * 
	 * @param location
	 * @param classNm
	 * @param content
	 */
	public static void exportMapper(String location,String classNm,String content){
		BufferedWriter bw = null;	
		FileWriter fw = null;
		try {
			File file = new File(location+"/mapper/"+classNm+"Mapper"+".java");
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}finally{
			close(bw,fw);
		}
	}
	/**
	 * 
	 * @param location
	 * @param classNm
	 * @param content
	 */
	public static void exportXml(String location,String classNm,String content){
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(location+"/xml/"+classNm+"Mapper"+".xml");
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}finally{
			close(bw,fw);
		}
	}
	/**
	 * 
	 * @param bw
	 * @param fw
	 */
	private static void close(BufferedWriter bw,FileWriter fw){
		if(bw != null){
			try {
				bw.close();
			} catch (IOException e) {					
				e.printStackTrace();
			}
		}
		if(fw != null){
			try {
				fw.close();
			} catch (IOException e) {					
				e.printStackTrace();
			}
		}

	}
}
