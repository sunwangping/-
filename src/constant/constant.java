package constant;

public class constant {
	public static int num = 5;
	public static String folderPath = "/images/folder.jpg";
	public static String folder1Path = "/images/folder1.jpg";
	public static String filePath = "/images/file.jpg";
	public static String file1Path = "/images/file1.jpg";
	public static String diskPath = "/images/disk.jpg";
	public static String imgPath = "/images/img1.jpg";
	
	public static int END = 255;
	public static int DISK = 0;
	public static int FOLDER = 1;
	public static int FILE = 2;
	
	public static int ERROR = -1;
	
	public static int flagRead = 0;
	public static int flagWrite = 1;
	
	public static int getHeight(int n){
		int a = 0;
		a = n / 4;
		if (n % 4 > 0){
			a++;
		}
		return a * 120;
	}
}
