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
		jPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/ϵͳ.png"))));
		jPanel.add(new JLabel(new ImageIcon(getClass().getResource("/images/ϵͳ.png"))));
		for(int i=2; i<fat.length; i++) {
			if(fat[i] == null) {
				JLabel  jLabel = new JLabel(new ImageIcon(getClass().getResource("/images/����.png")));
				jPanel.add(jLabel);
			}
			else if(fat[i].getObject() instanceof Folder) {
				JLabel  jLabel = new JLabel(new ImageIcon(getClass().getResource("/images/�ļ���.png")));
				jPanel.add(jLabel);
			}
			else if(fat[i].getObject() instanceof File) {
				JLabel  jLabel = new JLabel(new ImageIcon(getClass().getResource("/images/�ļ�.png")));
				jPanel.add(jLabel);
			}
		}
		return jPanel;
	}
}
