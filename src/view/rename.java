package view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import fileOperation.fileOperation;
import model.FAT;
import model.File;
import model.Folder;

public class rename {
	private FAT fat;
	private boolean isFile = false;
	private Component component;
	private String oldName = "";
	private String rename = "";
	private String path = "";
	private String oldPath = "";
	private Map<String, DefaultMutableTreeNode> map;
	private fileOperation fileOperation;

	public rename(Component component, FAT fat, Map<String, DefaultMutableTreeNode> map, fileOperation fileOperation) {
		this.fileOperation = fileOperation;
		this.map = map;
		this.fat = fat;
		this.component = component;
		this.init();
	}

	private void init() {
		if (fat.getProperty() == "File") {
			isFile = true;
			File file = (File) fat.getObject();
			oldName = file.getFileName();
			oldPath = file.getpath() + "\\" + oldName;
			path = file.getpath();
			rename = (String) JOptionPane.showInputDialog(component, "请输入名称", null, JOptionPane.PLAIN_MESSAGE, null,
					null, oldName);
			if (rename != null && rename != "" && !rename.equals(oldName)) {
				String path1 = ((File) fat.getObject()).getpath() + "\\" + rename;
				if (this.checkHasName(path1, isFile)) {
					JOptionPane.showMessageDialog(component, "已有该名字的文件了", "错误", JOptionPane.ERROR_MESSAGE,
							new ImageIcon(getClass().getResource("/images/错误.png")));
					return;
				}
				if (rename.contains("$") || rename.contains(".") || rename.contains("/")) {
					JOptionPane.showMessageDialog(component, "文件名包含\\\"$\\\",\\\".\\\",\\\"/\\\" 字符,不合法", "错误",
							JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/images/错误.png")));
					return;
				}
				((File) fat.getObject()).setFileName(rename);
			}
		} else {
			isFile = false;
			Folder folder = (Folder) fat.getObject();
			oldName = folder.getFoldername();
			oldPath = folder.getPath() + "\\" + oldName;
			path = folder.getPath();
			rename = (String) JOptionPane.showInputDialog(component, "请输入名称", null, JOptionPane.PLAIN_MESSAGE, null,
					null, oldName);
			if (rename != null && (rename != "" && !rename.equals(oldName))) {
				String path1 = ((Folder) fat.getObject()).getPath() + "\\" + rename;
				if (this.checkHasName(path1, false)) {
					JOptionPane.showMessageDialog(component, "已有该名字的文件夹了", "错误", JOptionPane.ERROR_MESSAGE,
							new ImageIcon(getClass().getResource("/images/错误.png")));
					return;
				}

				if (rename.contains("$") || rename.contains(".") || rename.contains("/")) {
					JOptionPane.showMessageDialog(component, "文件名包含\"$\",\".\",\"/\" 字符,不合法", "错误",
							JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/images/错误.png")));
					return;
				}

				((Folder) fat.getObject()).setFoldername(rename);
			}
		}

		// 更改map中的路径
		String newPath = path + "\\" + rename;
		fileOperation.modifyPath(oldPath, newPath);
		Set<String> set = map.keySet();
		List<String> setStr = new ArrayList<String>();
		setStr.addAll(set);
		for (String s : setStr) {
			if (s.contains(oldPath)) {
				DefaultMutableTreeNode n = map.get(s);
				String newPaths = s.replace(oldPath, newPath);
				map.remove(s);
				map.put(newPaths, n);
			}
		}
	}

	public boolean checkHasName(String path1, boolean isFile) {
		for (int i = 3; i < fileOperation.getFAT().length; i++) {
			if (fileOperation.getFAT()[i] != null) {
				// 文件
				if (isFile) {
					if (fileOperation.getFAT()[i].getProperty() == "File") {
						String path2 = ((File) (fileOperation.getFAT()[i].getObject())).getpath() + "\\"
								+ ((File) (fileOperation.getFAT()[i].getObject())).getFileName();
						if (path2.equals(path1)) {
							return true;
						}
					}
				} else {
					// 文件夹
					if (fileOperation.getFAT()[i].getProperty() == "Folder") {
						String path2 = ((Folder) (fileOperation.getFAT()[i].getObject())).getPath() + "\\"
								+ ((Folder) (fileOperation.getFAT()[i].getObject())).getFoldername();
						if (path2.equals(path1)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
