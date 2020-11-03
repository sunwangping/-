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

public class openFileFrame_rw extends JFrame{
	
	private JMenu jMenu;
	private JMenuItem jmi1,jmi2;
	private JMenuBar jmb;
	private JTextArea jta;
	private JFrame jf;
	private FAT fat;
	private fileOperation fileOperation;
	private File file;
	private String oldContent;
	private JPanel jp,jp4;
	private JLabel jl;
	private openFileTable openFileTable;
	private JTable jTable;
	
	public openFileFrame_rw(openFileTable openFileTable, FAT fat, fileOperation fileOperation, JPanel jp4, JTable jTable) {
		this.jf = this;
		this.fat = fat;
		this.fileOperation = fileOperation;
		this.file = (File)fat.getObject();
		this.jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.jp4 = jp4;
		this.openFileTable = openFileTable;
		this.jTable = jTable;
		initData();
		initOpenFileFrame();
		menuItemAddListener();
		JtaAddListener();
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
		
		oldContent = file.getFileContent();
		jta.setText(oldContent);
		jta.setLineWrap(true);
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
		jf.setTitle("打开文件（读写）");
		jf.setLayout(new BorderLayout());
		jf.add(jmb, BorderLayout.NORTH);
		jf.add(jta, BorderLayout.CENTER);
		jf.add(jp, BorderLayout.SOUTH);
		jf.addWindowListener(new WindowClosingListener());
	}
	
	public void saveData() {
		int oldlength = oldContent.length();
		int newlength = jta.getText().length();
		int start = oldlength / 64;
		int num = oldlength % 64;
		int more = oldlength % 64;
		int need = (more+newlength-oldlength) / 64;
		if(oldlength == 0) {
			if(newlength % 64 == 0)need = newlength / 64 - 1;
			else if(newlength < 64)need = 0;
			else need = newlength / 64 ;
		}
		if(num == 0 && oldlength != 0) need+=1;
		if(oldlength != 0 && (more+newlength-oldlength) % 64 == 0) need-=1;
		boolean success = fileOperation.GiveFileDiskAgain(this, need, fat);
		if(success) {
			file.setSize(newlength);
			file.setFileContent(jta.getText());
			file.setNumInFAT(file.getNumInFAT()+need);
		}
	}
	
	private void menuItemAddListener() {
		//保存
		jmi1.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!jta.getText().equals(file.getFileContent())) {
					saveData();
					file.setSize(jta.getText().length());
					jp4.removeAll();
					diskChange diskChange = new diskChange(jp4);
					jp4 = diskChange.disk();
					jp4.updateUI();
					openFileTable.initData();
					jTable.updateUI();
				}
			}
		});
		//退出
		jmi2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				fileOperation.removeOpenFile(fat);
				openFileTable.initData();
				jTable.updateUI();
			}
		});
	}
	
	public void JtaAddListener() {
		jta.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				StringBuffer text = new StringBuffer();
				text.append(jta.getText().length());
				jl.setText("当前文件长度："+text.toString());
				jp.add(jl);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});
	}
	
	class WindowClosingListener extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
			if (!jta.getText().equals(file.getFileContent())){
				int option = JOptionPane.showConfirmDialog(jf, "  还没有保存,是否保存", "确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/images/问号.png")));
				if (option == JOptionPane.YES_OPTION){
					saveData();
					jp4.removeAll();
					diskChange diskChange = new diskChange(jp4);
					jp4 = diskChange.disk();
					jp4.updateUI();
					file.setSize(jta.getText().length());
				}
				fileOperation.removeOpenFile(fat);
			}
			fileOperation.removeOpenFile(fat);
			openFileTable.initData();
			jTable.updateUI();
		}
		
	}
	
}
