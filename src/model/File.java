package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class File {
	private String fileName;  	//文件名	
	private String type;	  	//文件类型	
	private String property;  	//文件属性
	private int startDiskNum; 	//起始盘块号
	private String fileContent;	//文件内容
	private int numInFAT;			
	
	private String path; 	//文件路径	
	private int size;	 	//文件大小
	private Date buildTime;	//创建时间
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
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
