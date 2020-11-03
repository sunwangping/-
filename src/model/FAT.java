package model;

public class FAT {
	private int nextDiskNum;
	private String property;
	private Object object;
	
	public FAT(int nextDiskNum, String property, Object object) {
		super();
		this.nextDiskNum = nextDiskNum;
		this.property = property;
		this.object = object;
	}

	public int getnextDiskNum() {
		return nextDiskNum;
	}

	public void setnextDiskNum(int nextDiskNum) {
		this.nextDiskNum = nextDiskNum;
	}


	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	

}
