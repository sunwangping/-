package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Folder {
	private String folderName;	//Ŀ¼��
	private String property;	//����
	private int startDiskNum;	//��ʼ�̿��
	private boolean haveChild;	
	private int numInFAT;
	
	private String path;		//·��
	private int size;			//��С
	private Date buildTime;		//����ʱ��
	private int filenum;		//��Ŀ¼���ļ���
	
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��  HH:mm:ss");
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
