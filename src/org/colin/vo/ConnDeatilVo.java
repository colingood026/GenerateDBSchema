package org.colin.vo;
/**
 * @author Colin
 *
 */
public class ConnDeatilVo {
	private final String url;
	private final String userNm;
	private final String psd;
	private final String classNm;
	
	public static class Builder{
		private final String url;
		private final String userNm;
		private final String psd;
		private final String classNm;
		/**
		 * 
		 * @param url
		 * @param userNm
		 * @param psd
		 * @param classNm
		 */
		public Builder(String url,String userNm,String psd,String classNm){
			this.url=url;
			this.userNm=userNm;
			this.psd=psd;
			this.classNm=classNm;
		}
		
		public ConnDeatilVo build(){
			return new ConnDeatilVo(this);
		}
	}
	
	private ConnDeatilVo(Builder builder){
		url = builder.url;
		userNm = builder.userNm;
		psd = builder.psd;
		classNm = builder.classNm;
	}

	public String getUrl() {
		return url;
	}

	public String getUserNm() {
		return userNm;
	}

	public String getPsd() {
		return psd;
	}

	public String getClassNm() {
		return classNm;
	}


	
}
