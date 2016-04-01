package com.terapico.util.mail;

public class MailURLResource {

	/**
	 * @param args
	 */
	private byte[] content;
	private String contentType;
	private String fileName;
	
	public MailURLResource(String fileName,String contentType,byte[] content){
		this.contentType=contentType;
		this.fileName=fileName;
		this.content=content;
		
	}
	/**
	 * @return Returns the content.
	 */
	public byte[] getContent() {
		return content;
	}
	/**
	 * @param content The content to set.
	 */
	public void setContent(byte[] content) {
		this.content =  content;
	}
	/**
	 * @return Returns the contentType.
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType The contentType to set.
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * @return Returns the fileName.
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName The fileName to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
