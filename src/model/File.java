package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class File {
	private String fileName;  	//�ļ���	
	private String type;	  	//�ļ�����	
	private String property;  	//�ļ�����
	private int startDiskNum; 	//��ʼ�̿��
	private String fileContent;	//�ļ�����
	private int numInFAT;			
	
	private String path; 	//�ļ�·��	
	private int size;	 	//�ļ���С
	private Date buildTime;	//����ʱ��
	private boolean isReadOnly;	
	
	public File(String filename) {
		super();
		this.fileName = filename;
	}
	
	public File(String fileName, String path, int startDiskNum) {
		super();
		this.numInFAT = 1;
		this.fileName = fileName;
		this.path = path;
		this.startDiskNum = startDiskNum;
		this.property = "File";
		this.isReadOnly = false;
		this.numInFAT = 1;
		this.buildTime = new Date();
		this.fileContent = "";
		this.size = 0;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getProperty() {
		return property;
	}


	public void setProperty(String property) {
		this.property = property;
	}


	public int getStartDiskNum() {
		return startDiskNum;
	}


	public void setStartDiskNum(int startDiskNum) {
		this.startDiskNum = startDiskNum;
	}

	public String getFileContent() {
		return fileContent;
	}


	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}


	public int getNumInFAT() {
		return numInFAT;
	}


	public void setNumInFAT(int numInFAT) {
		this.numInFAT = numInFAT;
	}

	public String getpath() {
		return path;
	}


	public void setpath(String path) {
		this.path = path;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String getbuildTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��  HH:mm:ss");
		return format.format(buildTime);
	}


	public void setbuildTime(Date creratTime) {
		this.buildTime = creratTime;
	}


	public boolean isReadOnly() {
		return isReadOnly;
	}


	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}


	@Override
	public String toString() {
		return fileName;
	}
	
}
