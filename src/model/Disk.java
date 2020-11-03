package model;

public class Disk {
    private String diskNum;

	@Override
	public String toString() {
		return diskNum ;
	}

	public String getDiskNum() {
		return diskNum;
	}

	public void setDiskNum(String diskNum) {
		this.diskNum = diskNum;
	}

	public Disk(String diskNum) {
		super();
		this.diskNum = diskNum;
	}
}
