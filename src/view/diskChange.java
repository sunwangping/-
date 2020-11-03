package view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fileOperation.fileOperation;
import model.FAT;
import model.File;
import model.Folder;

public class diskChange {
	
	private FAT[] fat;
	private JPanel jPanel;
	public diskChange(JPanel jPanel) {
		this.jPanel = jPanel;
		this.fat = fileOperation.getFAT();
	}
	
	public JPanel disk() {
		jPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/系统.png"))));
		jPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/系统.png"))));
		for(int i=2; i<fat.length; i++) {
			if(fat[i] == null) {
				JLabel  jLabel = new JLabel(new ImageIcon(getClass().getResource("/images/磁盘.png")));
				jPanel.add(jLabel);
			}
			else if(fat[i].getObject() instanceof Folder) {
				JLabel  jLabel = new JLabel(new ImageIcon(getClass().getResource("/images/文件夹.png")));
				jPanel.add(jLabel);
			}
			else if(fat[i].getObject() instanceof File) {
				JLabel  jLabel = new JLabel(new ImageIcon(getClass().getResource("/images/文件.png")));
				jPanel.add(jLabel);
			}
		}
		return jPanel;
	}
}
