package model;

public class OFTLE {
	//�Ѵ��ļ��������Ͷ���
	private File file;
	private int flag;		// 0 �Զ���  1��д��
	private Pointer read;	//���ļ���λ��
	private Pointer write;	//д�ļ���λ��
	
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
