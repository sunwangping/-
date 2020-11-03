package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Folder {
	private String folderName;	//目录名
	private String property;	//属性
	private int startDiskNum;	//起始盘块号
	private boolean haveChild;	
	private int numInFAT;
	
	private String path;		//路径
	private int size;			//大小
	private Date buildTime;		//创建时间
	private int filenum;		//该目录的文件数
	
	public Folder(String folderName){
		super();
		this.folderName = folderName;
	}
	public Folder(String folderName, String path, int startDiskNum) {
		super();
		this.folderName = folderName;
		this.path = path;
		this.startDiskNum = startDiskNum;
		this.property = "Folder";
		this.buildTime = new Date();
		this.size = 64;
		this.numInFAT = 1;
	}
	public String getFoldername() {
		return folderName;
	}
	public void setFoldername(String foldername) {
		this.folderName = foldername;
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

	public boolean isHaveChild() {
		return haveChild;
	}
	public void setHaveChild(boolean haveChild) {
		this.haveChild = haveChild;
	}
	public int getNumInFAT() {
		return numInFAT;
	}
	public void setNumInFAT(int numInFAT) {
		this.numInFAT = numInFAT;
	}
	
	public int getFilenum() {
		return filenum;
	}
	public void setFilenum(int filenum) {
		this.filenum = filenum;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getBuildTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		return format.format(buildTime);
	}
	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}
	@Override
	public String toString() {
		return folderName;
	}
	
	
}
