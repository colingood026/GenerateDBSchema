package org.colin.util;

public class MethodUtils {
	
	public static final String CAPS = "    ";
	public static final String DOUBLE_CAPS = "        ";
	public static final String CHANGE_LINE = "\n";
	
	public static final String[] SPLITS = {"_"};
	/**
	 * xx_aa_bb... -> xxAaBb
	 * @param fieldNm
	 * @return
	 */
	public static String removeSplitForField(String fieldNm){		
		StringBuffer sb = new StringBuffer();
		
		for(String split:SPLITS){
			String[] spS = fieldNm.split(split);
			if(spS.length > 1){
				for(int i = 1; i < spS.length; i++){
					spS[i] = String.valueOf(spS[i].charAt(0)).toUpperCase() + spS[i].substring(1);
				}
				for(String sp:spS){
					sb.append(sp);
				}
			}else{
				sb.append(fieldNm);
			}			
		}
		return sb.toString();
	}
	/**
	 * xx_aa_bb... -> XxAaBb
	 * @param fieldNm
	 * @return
	 */
	public static String removeSplitForClassNm(String fieldNm){		
		StringBuffer sb = new StringBuffer();
		
		for(String split:SPLITS){
			String[] spS = fieldNm.split(split);
			spS[0] = String.valueOf(spS[0].charAt(0)).toUpperCase() + spS[0].substring(1);
			if(spS.length > 1){
				for(int i = 1; i < spS.length; i++){
					spS[i] = String.valueOf(spS[i].charAt(0)).toUpperCase() + spS[i].substring(1);
				}
				for(String sp:spS){
					sb.append(sp);
				}
			}else{
				sb.append(fieldNm);
			}			
		}
		return sb.toString();
	}
}
