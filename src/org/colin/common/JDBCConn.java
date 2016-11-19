package org.colin.common;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.colin.generate.mybatis.GenerateMybatisJavaBean;
import org.colin.generate.mybatis.GenerateMybatisMapper;
import org.colin.generate.mybatis.GenerateMybatisXml;
import org.colin.util.DriverClassEnum;
import org.colin.util.ExportUtil;
import org.colin.util.ImportJarEnum;
import org.colin.util.MethodUtils;
import org.colin.util.SqlTypeTransferEnum;
import org.colin.vo.ConnDeatilVo;
import org.colin.vo.JavaDataVo;

/**
 * @author Colin
 *
 */
public class JDBCConn {

	public static void main(String[] args){
		ConnDeatilVo connDetail = createConnDetail();
		Set<String> tableNms = new HashSet<>();
		tableNms.add("IP_DATA");
		tableNms.add("BAS_SITE");
		tableNms.add("NETWORK_DATA");
		tableNms.add("PUB_EMPLOYEE");
		//
		List<JavaDataVo> javaDataVos = getJavaDatas(connDetail,tableNms);
		//
		String location = "D:/Generate_Test_xxx";
		location = ExportUtil.createDirectory(location);
		String packageNm = location.split("/")[1];

		if(location != null){
			for(JavaDataVo vo:javaDataVos){
				Set<String> importJars = vo.getImportJars();
				String classNm = vo.getClassNm();
				String tableNm = vo.getTableNm();
				List<String[]> fields = vo.getFields();
				//
				String bean = GenerateMybatisJavaBean.build(packageNm,importJars, fields, classNm);			
				ExportUtil.exportBean(location, classNm, bean);
				//
				String mapper = GenerateMybatisMapper.build(packageNm,importJars, fields, classNm);
				ExportUtil.exportMapper(location, classNm, mapper);
				//
				String xml = GenerateMybatisXml.build(packageNm,fields, classNm, tableNm);
				ExportUtil.exportXml(location, classNm, xml);
				//
				System.out.println("****************************************");
			}
		}

		
		
	}

	public static List<JavaDataVo> getJavaDatas(ConnDeatilVo connDeatilVo,Set<String> tableNms) {
		
		List<JavaDataVo> javaDataVos = new ArrayList<>();
		
		Connection conn=null;
		ResultSet rs=null;
		DatabaseMetaData dma = null;
		String catalog = null;
		String schemaPattern = null;		
		String columnNamePattern = null;

		for(String tableNm:tableNms){
			// *
			Set<String> importJars = new HashSet<>();
			// *
			List<String[]> fields = new ArrayList<>();
				try {
					Class.forName(connDeatilVo.getClassNm());
					conn = DriverManager.getConnection(connDeatilVo.getUrl(), connDeatilVo.getUserNm(), connDeatilVo.getPsd());				
				
					dma = conn.getMetaData();
					rs = dma.getColumns(catalog, schemaPattern, tableNm, columnNamePattern);

					while (rs.next()){					
						String columnNm = rs.getString("COLUMN_NAME");
						String sqlType = rs.getString("TYPE_NAME");
						String javaType = SqlTypeTransferEnum.getJavaTypeBySqlType(sqlType);
						String importJar = ImportJarEnum.getImportStrByTypeName(javaType);
						if(importJar != null){
							importJars.add(importJar);
						}
						fields.add(new String[]{columnNm.toLowerCase(),javaType});
					}
					
					// *
					String classNm = MethodUtils.removeSplitForClassNm(tableNm.toLowerCase());
					
					JavaDataVo javaDataVo = 
							new JavaDataVo.Builder(classNm, tableNm, importJars, fields).build();
					
					javaDataVos.add(javaDataVo);
					
				} catch (ClassNotFoundException e) {					
					e.printStackTrace();
				} catch (SQLException e) {					
					e.printStackTrace();
				} finally{
					if(rs != null){
						try {
							rs.close();
						} catch (SQLException e) {							
							e.printStackTrace();
						}
					}
					if(conn != null){
						try {
							conn.close();
						} catch (SQLException e) {							
							e.printStackTrace();
						}
					}
				}
		}
		
		
		
		
		return javaDataVos;
	}
	

	public static ConnDeatilVo createConnDetail(){
		String URL = "jdbc:sqlserver://localhost:1433;database=CTBC_HP";
		String USER_NAME = "sa";
		String PASS = "123qweaS";
		String CLASS_NAME = DriverClassEnum.getClassNmByDbNm("msSql");
		ConnDeatilVo connDeatilVo = 
				new ConnDeatilVo.Builder(URL, USER_NAME, PASS, CLASS_NAME).build();
		return connDeatilVo;
	}


}
