package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fileOperation.fileOperation;
import model.FAT;
import model.File;
import view.openFileFrame_rw.WindowClosingListener;

public class openFileFrame_rom extends JFrame{
	
	private JMenu jMenu;
	private JMenuItem jmi1,jmi2;
	private JMenuBar jmb;
	private JTextArea jta;
	private JFrame jf;
	private FAT fat;
	private fileOperation fileOperation;
	private File file;
	private String Content;
	private JPanel jp;
	private JLabel jl;
	private JTable jTable;
	
	public openFileFrame_rom(openFileTable openFileTable, FAT fat, fileOperation fileOperation, JTable jTable) {
		this.jf = this;
		this.fat = fat;
		this.fileOperation = fileOperation;
		this.file = (File)fat.getObject();
		this.jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.jTable = jTable;
		initData();
		initOpenFileFrame();
	}
	
	public void initData() {
		jta = new JTextArea();
		jmb = new JMenuBar();
		jMenu = new JMenu("操作");
		jmi1 = new JMenuItem("保存");
		jmi2 = new JMenuItem("退出");
		jmb.add(jMenu);
		jMenu.add(jmi1);
		jMenu.add(jmi2);
		
		Content = file.getFileContent();
		jta.setText(Content);
		jta.setLineWrap(true);
		jta.setEditable(false);
		jl = new JLabel();
		StringBuffer text = new StringBuffer();
		text.append(jta.getText().length());
		jl.setText("当前文件长度："+text.toString());
		jp.add(jl);
	}
	
	public void initOpenFileFrame() {
		jf.setSize(400, 300);
		jf.setResizable(false);
		jf.setLocation(450, 250);
		jf.setVisible(true);
		jf.setTitle("打开文件（只读）");
		jf.setLayout(new BorderLayout());
		jf.add(jmb, BorderLayout.NORTH);
		jf.add(jta, BorderLayout.CENTER);
		jf.add(jp, BorderLayout.SOUTH);
		jf.addWindowListener(new WindowClosingListener());
	}
	
	class WindowClosingListener extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
			fileOperation.removeOpenFile(fat);
		}
		
	}
}
