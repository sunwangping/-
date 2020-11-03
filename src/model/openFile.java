package model;

import java.util.ArrayList;
import java.util.List;

public class openFile {
	// 已打开文件登记表定义

	private List<OFTLE> filesList;
	private int length;

	public openFile() {
		super();
		filesList = new ArrayList<OFTLE>(5);
		length = 0;
	}

	public void addFile(OFTLE oftle) {
		filesList.add(oftle);
	}

	public void removeFile(OFTLE oftle) {
		filesList.remove(oftle);
	}

	public List<OFTLE> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<OFTLE> filesList) {
		this.filesList = filesList;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
