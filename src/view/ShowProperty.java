package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import model.FAT;
import model.File;
import model.Folder;

public class ShowProperty extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame jf;
	private JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
	private FAT fat;
	private JButton jb;
	private JPanel jp;
	
	public ShowProperty(FAT fat) {
		this.jf = this;
		this.fat = fat;
		this.setLocation(600, 200);
		initFrame();
		initData();
		addButtonListener();
		addLabelListener();
	}
	
	public void initFrame() {
		this.setLayout(new FlowLayout());
		this.setTitle("属性");
		this.setSize(280, 450);
		this.setVisible(true);
	}
	
	public void initData() {
		if(fat.getProperty() == "Folder") {
			Folder folder = (Folder)(fat.getObject());
			jl1 = new JLabel("名字 :                        " + folder.getFoldername());
			jl2 = new JLabel("类型 :                        " + "文件夹");
			jl3 = new JLabel("路径 :                        " + folder.getPath());
			jl4 = new JLabel("占用空间大小:       " + folder.getSize());
			jl5 = new JLabel("创建日期:          " + folder.getBuildTime());
			jl3.setToolTipText(folder.getPath());
			jl5.setToolTipText(folder.getBuildTime());
			jl6 = new JLabel("占用盘块：              "+folder.getNumInFAT());
			jl7 = new JLabel("起始盘块：              "+folder.getStartDiskNum());
			jl8 = new JLabel("文件数目：               "+folder.getFilenum());
			jl9 = new JLabel("");
			jb = new JButton("确定");
			jp = new JPanel();
			jl1.setPreferredSize(new Dimension(230, 40));
			jl2.setPreferredSize(new Dimension(230, 40));
			jl3.setPreferredSize(new Dimension(230, 40));
			jl4.setPreferredSize(new Dimension(230, 40));
			jl5.setPreferredSize(new Dimension(230, 40));
			jl6.setPreferredSize(new Dimension(230, 40));
			jl7.setPreferredSize(new Dimension(230, 40));
			jl8.setPreferredSize(new Dimension(230, 40));
			jp.add(jb);
			this.add(jl1);
			this.add(jl2);
			this.add(jl3);
			this.add(jl4);
			this.add(jl5);
			this.add(jl6);
			this.add(jl7);
			this.add(jl8);
			this.add(jl9);
			this.add(jp);
		}
		else if(fat.getProperty() == "File"){
			File file =  (File)(fat.getObject());
			jl1 = new JLabel("名字 :                        " + file.getFileName());
			jl2 = new JLabel("类型 :                        " + "文件");
			jl3 = new JLabel("路径 :                        " + file.getpath());
			jl4 = new JLabel("占用空间大小:       " + file.getSize()+"B");
			jl5 = new JLabel("创建日期:                " + file.getbuildTime());
			jl3.setToolTipText(file.getpath());
			jl5.setToolTipText(file.getbuildTime());
			jl6 = new JLabel("占用盘块：             "+file.getNumInFAT());
			jl7 = new JLabel("起始盘块：             "+file.getStartDiskNum());
			jl8 = new JLabel("");
			jb = new JButton("确定");
			jp = new JPanel();
			jl1.setPreferredSize(new Dimension(230, 40));
			jl2.setPreferredSize(new Dimension(230, 40));
			jl3.setPreferredSize(new Dimension(230, 40));
			jl4.setPreferredSize(new Dimension(230, 40));
			jl5.setPreferredSize(new Dimension(230, 40));
			jl6.setPreferredSize(new Dimension(230, 40));
			jl7.setPreferredSize(new Dimension(230, 40));
			jp.add(jb);
			this.add(jl1);
			this.add(jl2);
			this.add(jl3);
			this.add(jl4);
			this.add(jl5);
			this.add(jl6);
			this.add(jl7);
			this.add(jl8);
			this.add(jp);
		}

	}
	
	public void addButtonListener() {
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
			}
		});
	}
	
	public void addLabelListener() {
		jl3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				jl3.getToolTipText();
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		jl5.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				jl5.getToolTipText();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
