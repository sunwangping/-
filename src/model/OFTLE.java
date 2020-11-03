package model;

public class OFTLE {
	//已打开文件表项类型定义
	private File file;
	private int flag;		// 0 以读打开  1以写打开
	private Pointer read;	//读文件的位置
	private Pointer write;	//写文件的位置
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Pointer getRead() {
		return read;
	}
	public void setRead(Pointer read) {
		this.read = read;
	}
	public Pointer getWrite() {
		return write;
	}
	public void setWrite(Pointer write) {
		this.write = write;
	}
	
	
}
