package fileOperation;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import model.FAT;
import model.File;
import model.Folder;
import model.OFTLE;
import model.openFile;
import view.diskChange;
import view.introduce;

public class fileOperation {
	private static FAT[] FAT;
	private static openFile openFile;

	public fileOperation() {
		FAT = new FAT[128];
		openFile = new openFile();
		FAT[0] = new FAT(255, "Disk", null);
		FAT[1] = new FAT(255, "Disk", null);
		FAT[2] = new FAT(255, "Folder", new Folder("C:"));
	}
	/**
	 * 添加打开的文件
	 * @param fat
	 * @param flag
	 */
	public void addOpenFile(FAT fat, int flag) {
		OFTLE oftle = new OFTLE();
		oftle.setFile((File) fat.getObject());
		oftle.setFlag(flag);
		openFile.addFile(oftle);
		openFile.setLength(openFile.getLength() + 1);
	}

	/**
	 * 移除打开的文件
	 * @param fat
	 */
	public void removeOpenFile(FAT fat) {
		for (int i = 0; i < openFile.getFilesList().size(); i++) {
			if (openFile.getFilesList().get(i).getFile() == (File) fat.getObject()) {
				openFile.getFilesList().remove(i);
				openFile.setLength(openFile.getLength() - 1);
			}
		}
	}

	/**
	 * 检查是否为打开的文件
	 * @param fat
	 * @return
	 */
	public boolean checkOpenFile(FAT fat) {
		for (int i = 0; i < openFile.getFilesList().size(); i++)
			if (openFile.getFilesList().get(i).getFile() == (File) fat.getObject())
				return true;
		return false;
	}

	/**
	 * 查找空磁盘块
	 * @return
	 */
	public int searchEmptyDiskInFAT() {
		for (int i = 3; i < FAT.length; i++) {
			if (FAT[i] == null)
				return i;
		}

		return -1;
	}

	/**
	 * 所有的空磁盘块
	 * @return
	 */
	public int allEmptyDiskInFAT() {
		int num = 0;
		for (int i = 3; i < FAT.length; i++)
			if (FAT[i] == null)
				num++;
		return num;
	}

	/**
	 * 所有已用的磁盘块数
	 * @return
	 */
	public int allUsedDiskInFAT() {
		int num = 0;
		for (int i = 3; i < FAT.length; i++)
			if (FAT[i] != null)
				num++;
		return num;
	}

	/**
	 * 创建文件
	 * @param component
	 * @param path
	 * @param nodeMap
	 * @return
	 */
	public int createFile(JPanel jPanel, Component component, String path, Map<String, DefaultMutableTreeNode> nodeMap) {
		String fileName = null;
		boolean flag = true;
		int idx = 1;
		do {
			fileName = "新建文件";
			flag = true;
			fileName += idx;
			for (int i = 3; i < FAT.length; i++) {

				if (FAT[i] != null)
					if (FAT[i].getProperty() == "File") {
						File file = (File) FAT[i].getObject();
						if (path.equals(file.getpath()))
							if (fileName.equals(file.getFileName()))
								flag = false;
					}

			}
			idx++;
		} while (!flag);
		int num = searchEmptyDiskInFAT();
		if (num == -1)
			return -1;
		else {
			File file = new File(fileName, path, num);
			FAT[num] = new FAT(255, "File", file);
			String parent = nodeMap.get(path).toString();
//			System.out.println(parent);
//			System.out.println("path="+path);
			
			//parent为父目录，path为当前到父目录的路径
			if(getFolderItem(component, path, parent)) {
				diskChange diskChange = new diskChange(jPanel);
				diskChange.disk();
				return num;
			}
			else return -1;
		}
	}

	/**
	 * 创建文件夹
	 * @param component
	 * @param path
	 * @param nodeMap
	 * @return
	 */
	public int createFolder(JPanel jPanel, Component component, String path, Map<String, DefaultMutableTreeNode> nodeMap) {

		String folderName = null;
		boolean flag = true;
		int idx = 1;
		do {
			folderName = "新建文件夹";
			flag = true;
			folderName += idx;
			for (int i = 3; i < FAT.length; i++) {
				if (FAT[i] != null)
					if (FAT[i].getProperty() == "Folder") {
						Folder Folder = (Folder) FAT[i].getObject();
						if (path.equals(Folder.getPath()))
							if (folderName.equals(Folder.getFoldername()))
								flag = false;
					}
			}
			idx++;
		} while (!flag);

		int num = searchEmptyDiskInFAT();
		if (num == -1)
			return -1;
		else {
			Folder Folder = new Folder(folderName, path, num);
			FAT[num] = new FAT(255, "Folder", Folder);
			String parent = nodeMap.get(path).toString();
//			System.out.println(parent);
//			System.out.println("path="+path);
			if(getFolderItem(component, path, parent)) {
				diskChange diskChange = new diskChange(jPanel);
				diskChange.disk();
				return num;
			}
			else return -1;
		}

	}
	
	/**
	 * 删除登记项
	 * @param path
	 * @param nodeMap
	 */
	public void deleteFolderItem(String path,  Map<String, DefaultMutableTreeNode> nodeMap) {
		int temp;
		int tempnext = 0;
		int tempDisk = 0;
		int tempNum;
		String parent = nodeMap.get(path).toString();
//		System.out.println("path="+path);
//		System.out.println("parent="+parent);
		for (int i = 3; i < FAT.length; i++) {
			if (FAT[i] != null) {
				if (FAT[i].getObject() instanceof Folder && FAT[i].getnextDiskNum() == 255) {
					Folder folder = ((Folder) FAT[i].getObject());
					if ((folder.getPath()+"\\"+folder.getFoldername()).equals(path) && folder.getFoldername().equals(parent)) {			
						if(((folder.getFilenum()-1) % 8) == 0 && (folder.getFilenum()-1) != 0) {
							tempNum = folder.getFilenum()-1;
							tempDisk = folder.getNumInFAT()-1;
							temp = i;
//							System.out.println(tempNum);
							FAT[i] = null;
							for (int j = 3; j < FAT.length; j++) {
								if (FAT[j] != null) {
									if (FAT[j].getObject() instanceof Folder) {
										if(FAT[j].getnextDiskNum() == temp) {
											FAT[j].setnextDiskNum(255);
										}
										if ((folder.getPath()+"\\"+folder.getFoldername()).equals(path) && folder.getFoldername().equals(parent)) {
											folder.setFilenum(tempNum);
											folder.setNumInFAT(tempDisk);
											System.out.println(tempDisk);
										}
									}
								}
							}
						}
						else {
							tempNum = folder.getFilenum()-1;
							for (int j = 3; j < FAT.length; j++) {
								if (FAT[j] != null) {
									if (FAT[j].getObject() instanceof Folder) {
										if ((folder.getPath()+"\\"+folder.getFoldername()).equals(path) && folder.getFoldername().equals(parent)) {
											folder.setFilenum(tempNum);
										}
									}
								}
							}
						}
						break;
					}
				}
			}
		}
	}

	/**
	 * 获取当前文件夹下的文件数目
	 * @param component
	 * @param path
	 * @param parent
	 * @return
	 */
	public boolean getFolderItem(Component component, String path, String parent) {
//		System.out.println(path+"    "+parent);
		Folder folder0 = ((Folder) FAT[2].getObject());
		folder0 = new Folder("C:", null, 2);
		if (path.equals("C:") && parent.equals("C")) {
//			System.out.println("输出1");
			folder0.setFilenum(folder0.getFilenum() + 1);
			if((folder0.getFilenum() + 1) % 8 == 1) {
				if(!GiveFolderItemDiskAgain(folder0)) {
					return false;
				}
			}
		}
		
		for (int i = 3; i < FAT.length; i++) {
			if (FAT[i] != null) {
				if (FAT[i].getObject() instanceof Folder) {
					Folder folder = ((Folder) FAT[i].getObject());
//					System.out.println("path="+path);
//					System.out.println("folder.getPath()="+folder.getPath()+folder.getFoldername());
					if ((folder.getPath()+"\\"+folder.getFoldername()).equals(path) && folder.getFoldername().equals(parent)) {
//						System.out.println("输出2");
						folder.setFilenum(folder.getFilenum() + 1);
						if((folder.getFilenum() % 8) == 1 && folder.getFilenum() != 1) {
							if(!GiveFolderItemDiskAgain(folder)) {
								return false;
							}
						}
						break;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 修改路径
	 * @param oldPath
	 * @param newPath
	 */
	public void modifyPath(String oldPath, String newPath) {
		for (int i = 3; i < FAT.length; i++) {
			if (FAT[i] != null) {
				if (FAT[i].getProperty() == "File") {
					if (((File) FAT[i].getObject()).getpath().contains(oldPath))
						((File) FAT[i].getObject())
								.setpath(((File) FAT[i].getObject()).getpath().replace(oldPath, newPath));
				} else if (FAT[i].getProperty() == "Folder") {
					if (((Folder) FAT[i].getObject()).getPath().contains(oldPath))
						((Folder) FAT[i].getObject())
								.setPath(((Folder) FAT[i].getObject()).getPath().replace(oldPath, newPath));
				}
			}
		}
	}

	/**
	 * 分配目录登记项的磁盘块（一个磁盘块存放8个登记项）
	 * @param folder
	 * @return
	 */
	private boolean GiveFolderItemDiskAgain(Folder folder) {
		int count = 1;
		int begin = folder.getStartDiskNum();
		int next = FAT[begin].getnextDiskNum();
		while (next != 255) {
			count++;
			if (FAT[next].getnextDiskNum() == 255)
				begin = next;
			next = FAT[next].getnextDiskNum();
		}
			int empty = this.searchEmptyDiskInFAT();
			if (empty == -1) {
				return false;
			}
			else {
				FAT[begin].setnextDiskNum(empty); 
				FAT[empty] = new FAT(255, "Folder", folder);
				folder.setNumInFAT(count+1);
				return true;
			}
	}
	
	/**
	 * 分配文件的磁盘块
	 * @param component
	 * @param needDiskNum
	 * @param fat
	 * @return
	 */
	public boolean GiveFileDiskAgain(Component component, int needDiskNum, FAT fat) {
		int count = 1;
		int begin = ((File) fat.getObject()).getStartDiskNum();
		int next = FAT[begin].getnextDiskNum();
		while (next != 255) {
			count++;
			if (FAT[next].getnextDiskNum() == 255)
				begin = next;
			next = FAT[next].getnextDiskNum();
		}

		if (needDiskNum > this.allEmptyDiskInFAT()) {
			JOptionPane.showMessageDialog(component, "保存的内容已经超过磁盘的容量");
			return false;
		} 
		else {
			if(needDiskNum != 0) {
				int empty = this.searchEmptyDiskInFAT();
				FAT[begin].setnextDiskNum(empty);
				for (int i = 1; i <= needDiskNum; i++) {
					System.out.println(i);
					empty = this.searchEmptyDiskInFAT();
					if (i == needDiskNum)
						FAT[empty] = new FAT(255, "File", (File) fat.getObject());
					else {
						FAT[empty] = new FAT(255, "File", (File) fat.getObject());
						int nextEmpty = this.searchEmptyDiskInFAT();
						FAT[empty].setnextDiskNum(nextEmpty);
					}
				}
				return true;
			}
			
		}
		return true;
	}

	/**
	 * 删除文件/文件夹
	 * @param jPanel
	 * @param fat
	 * @param map
	 */
	public void delete(JPanel jPanel, FAT fat, Map<String, DefaultMutableTreeNode> map) {
		if (fat.getProperty() == "File") {
			for (int i = 0; i < openFile.getFilesList().size(); i++)
				if (openFile.getFilesList().get(i).getFile().equals(fat.getObject())) {
					JOptionPane.showMessageDialog(jPanel, "文件正打开着，不能删除", "错误", JOptionPane.WARNING_MESSAGE,
							new ImageIcon(getClass().getResource("/images/感叹号.png")));
					return;
				}
			for (int i = 3; i < FAT.length; i++) {
				if (FAT[i] != null && FAT[i].getProperty() == "File") {
					if (((File) FAT[i].getObject()).equals((File) fat.getObject())) {
						FAT[i] = null;
					}

				}
			}
		} else {
			String path = ((Folder) fat.getObject()).getPath();
			String folderPath = ((Folder) fat.getObject()).getPath() + "\\"
					+ ((Folder) fat.getObject()).getFoldername();
			int index = 0;
			for (int i = 3; i < FAT.length; i++) {
				if (FAT[i] != null) {
					Object obj = FAT[i].getObject();
					if (FAT[i].getProperty() == "Folder") {
						if (((Folder) obj).getPath().equals(folderPath)) {
							JOptionPane.showMessageDialog(jPanel, "文件夹不为空，不能删除", "错误", JOptionPane.ERROR_MESSAGE);
							return;
						}
					} else {
						if (((File) obj).getpath().equals(folderPath)) {
							JOptionPane.showMessageDialog(jPanel, "文件夹不为空，不能删除", "错误", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					if (FAT[i].getProperty() == "Folder") {
						if (((Folder) FAT[i].getObject()).equals((Folder) fat.getObject())) {
							index = i;
						}
					}
				}
			}

			FAT[index] = null;
			DefaultMutableTreeNode parentNode = map.get(path);
			parentNode.remove(map.get(folderPath));
			map.remove(folderPath);
		}
	}

	public List<FAT> getFATs(String path) {
		List<FAT> fats = new ArrayList<FAT>();
		for (int i = 3; i < FAT.length; i++) {
			if (FAT[i] != null) {
				if (FAT[i].getObject() instanceof Folder) {
					if (((Folder) FAT[i].getObject()).getPath().equals(path) && FAT[i].getnextDiskNum() == 255)
						fats.add(FAT[i]);
				} else {
					if (FAT[i].getObject() instanceof File)
						if (((File) (FAT[i].getObject())).getpath().equals(path) && FAT[i].getnextDiskNum() == 255)
							fats.add(FAT[i]);
				}
			}

		}

		return fats;
	}

	public static FAT getFAT(int idx) {
		return FAT[idx];
	}

	public static FAT[] getFAT() {
		return FAT;
	}

	public static void setFAT(FAT[] fAT) {
		FAT = fAT;
	}

	public static openFile getOpenFile() {
		return openFile;
	}

	public static void setOpenFile(openFile openFile) {
		fileOperation.openFile = openFile;
	}
}
