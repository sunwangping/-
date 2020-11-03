package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import constant.constant;
import fileOperation.*;
import model.FAT;
import model.File;
import model.Folder;

public class Run extends JFrame {
	public static int n = 0;
	private JTable jTable;
	private JScrollPane jpGrid;
	private JPanel jpSearch, jpMain, jp3, jp4, jp5, jp6, jp7, jp8;
	private JSplitPane jsp;
	private JMenuBar jmb;
	private JMenu jm1, jm2, jm3, jm4;
	private JMenuItem jmi1, jmi2, jmi3, jmi4;
	private Tree jtr;
	private JTextField jtf;
	private JLabel jl1, jl2, jl3;
	private fileOperation fOp;
	private List<FAT> fatList;
	private Label[] labels;
	private Map<String, DefaultMutableTreeNode> nodeMap;
	private diskChange diskChange;
	private openFileTable oft;
	private int select = 0;

	public Run(String title) {
		super(title);
		creatJMenuBar();
		oft = new openFileTable();
		fatList = new ArrayList<FAT>();
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		initMap();
		initFileOperation();
		initMainFrame();

		creatSearch();
		contentPane.add(jpMain, BorderLayout.CENTER);
		contentPane.add(jmb, BorderLayout.NORTH);
		addMenuListener();
	}

	public void initMainFrame() {
		jTable = new JTable(oft);
		jTable.setRowHeight(25);
		for (int j = 0; j < jTable.getColumnCount(); j++) {
			jTable.getColumnModel().getColumn(j).setPreferredWidth(70);
		}
		jp3 = new JPanel();
		GridLayout layout = new GridLayout(16, 8);
		layout.setHgap(10);
		layout.setVgap(10);
		jp4 = new JPanel(layout);
		jp5 = new JPanel(new BorderLayout());
		initDiskChange(jp4);
		jp4 = diskChange.disk();
		jp3.setPreferredSize(new Dimension(10, 400));
		jpGrid = new JScrollPane(jp4);
		jpGrid.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jpGrid.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jpGrid.setPreferredSize(new Dimension(270, 400));
		jpGrid.setViewportView(jp4);
		jp5.setPreferredSize(new Dimension(10, 150));
		notes();
		jp5.add(jp6, BorderLayout.WEST);
		jp5.add(jp7, BorderLayout.CENTER);
		jp5.add(jp8, BorderLayout.EAST);
		jtr = new Tree();
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());
		jpMain.add(jtr, BorderLayout.WEST);
		jpMain.add(jp3, BorderLayout.CENTER);
		jpMain.add(jpGrid, BorderLayout.EAST);
		jpMain.add(jp5, BorderLayout.SOUTH);
	}

	/**
	 * 
	 */
	public void notes() {
		JScrollPane jScrollPane = new JScrollPane(jTable);
		JPanel jtemp = new JPanel();
		jtemp.setPreferredSize(new Dimension(700, 10));
		jp6 = new JPanel(new BorderLayout());
		jp7 = new JPanel();
		jp8 = new JPanel(new GridLayout(2, 2, 20, 20));
		jp6.setPreferredSize(new Dimension(680, 100));
		jp7.setPreferredSize(new Dimension(100, 100));
		jp8.setPreferredSize(new Dimension(270, 100));
//		jp6.setBackground(Color.white);
		jp6.add(jtemp,BorderLayout.NORTH);
		jp6.add(jScrollPane,BorderLayout.CENTER);
		JLabel jl1 = new JLabel("文件分配表", new ImageIcon(getClass().getResource("/images/系统.png")), JLabel.CENTER);
		JLabel jl2 = new JLabel("文件", new ImageIcon(getClass().getResource("/images/文件.png")), JLabel.CENTER);
		JLabel jl3 = new JLabel("文件夹", new ImageIcon(getClass().getResource("/images/文件夹.png")), JLabel.CENTER);
		JLabel jl4 = new JLabel("空磁盘", new ImageIcon(getClass().getResource("/images/磁盘.png")), JLabel.CENTER);

		jl1.setHorizontalAlignment(JLabel.LEFT);
		jl1.setIconTextGap(15);
		jl2.setHorizontalAlignment(JLabel.LEFT);
		jl2.setIconTextGap(15);
		jl3.setHorizontalAlignment(JLabel.LEFT);
		jl3.setIconTextGap(15);
		jl4.setHorizontalAlignment(JLabel.LEFT);
		jl4.setIconTextGap(15);

		jp8.add(jl1);
		jp8.add(jl2);
		jp8.add(jl3);
		jp8.add(jl4);
		jp8.add(jl1);
		jp8.add(jl2);
		jp8.add(jl3);
		jp8.add(jl4);
	}

	/**
	 * 绝对路径
	 */
	public void creatSearch() {
		jl1 = new JLabel("                   路径：");
		jtf = new JTextField("C:");
		jtf.setPreferredSize(new Dimension(450, 20));
		jpSearch = new JPanel();
		jpSearch.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpSearch.setPreferredSize(new Dimension(1000, 30));
		jpSearch.add(jl1);
		jpSearch.add(jtf);
		jpMain.add(jpSearch, BorderLayout.NORTH);
	}

	/**
	 * 菜单栏
	 */
	public void creatJMenuBar() {
		jmb = new JMenuBar();
		jm1 = new JMenu("新建");
		jm2 = new JMenu("操作");
		jm3 = new JMenu("系统");
		jm4 = new JMenu("更多");
		jmi1 = new JMenuItem("新建文件");
		jmi2 = new JMenuItem("新建文件夹");
		jmi3 = new JMenuItem("系统介绍");
		jmi4 = new JMenuItem("关于我们");

		jm1.add(jmi1);
		jm1.addSeparator();
		jm1.add(jmi2);
		jm3.add(jmi3);
		jm4.add(jmi4);
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
	}

	public void initFileOperation() {
		fOp = new fileOperation();
	}

	public void initMap() {
		nodeMap = new HashMap<String, DefaultMutableTreeNode>();
	}

	public void initDiskChange(JPanel jPanel) {
		diskChange = new diskChange(jPanel);
	}

	/**
	 * 菜单栏监听
	 */
	public void addMenuListener() {

		jmi3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new introduce(getContentPane());
			}
		});

		jmi4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print("更多");
			}
		});
	}

	public class Tree extends JPanel {
		private JTree jTree;
		private DefaultMutableTreeNode rootNode;
		private JScrollPane jsp1, jsp2;
		private JSplitPane jsp;
		private JPanel jp;
		private JPopupMenu pm1, pm2, pm3;
		private JMenuItem mi0, mi1, mi2, mi3, mi4, mi5, mi6, mi7, mi8, mi9, mi10;
		private Images[] jL;

		public Tree() {
			this.initMenuItem();
			this.initMenuItemJLabel();
			this.initTreeFrame();
			this.jpAddListener();
			this.treeAddListener();
			this.menuItemAddListener();
			this.add(jsp);
		}

		public void initMenuItem() {
			pm1 = new JPopupMenu();
			mi1 = new JMenuItem("新建文件");
			mi2 = new JMenuItem("新建文件夹");
			pm1.add(mi1);
			pm1.addSeparator();
			pm1.add(mi2);
		}

		public void initMenuItemJLabel() {
			pm2 = new JPopupMenu();
			pm3 = new JPopupMenu();
			mi0 = new JMenuItem("打开（只读）");
			mi3 = new JMenuItem("打开（读写）");
			mi4 = new JMenuItem("重命名");
			mi5 = new JMenuItem("删除");
			mi6 = new JMenuItem("属性");

			mi7 = new JMenuItem("打开");
			mi8 = new JMenuItem("重命名");
			mi9 = new JMenuItem("删除");
			mi10 = new JMenuItem("属性");

			pm2.add(mi7);
			pm2.add(mi8);
			pm2.add(mi9);
			pm2.add(mi10);

			pm3.add(mi0);
			pm3.add(mi3);
			pm3.add(mi4);
			pm3.add(mi5);
			pm3.add(mi6);
		}

		public void initTreeFrame() {
			rootNode = new DefaultMutableTreeNode("C");
			nodeMap.put("C:", rootNode);
			jTree = new JTree(rootNode);
			jsp1 = new JScrollPane(jTree);
			jsp1.setPreferredSize(new Dimension(200, 350));
			jp = new JPanel();
			jp.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp.setBackground(Color.white);
			jp.add(pm1);
			jsp2 = new JScrollPane(jp);
			jsp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jsp2.setBackground(Color.white);
			jsp2.setPreferredSize(new Dimension(482, 350));
			jsp2.setViewportView(jp);
			jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jsp1, jsp2);
			jsp.setDividerSize(0);
			jsp.setDividerLocation(200);
			jsp.setEnabled(false);
		}

		public void jpAddListener() {
			jp.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					int mods = e.getModifiers();
					if ((mods & InputEvent.BUTTON3_MASK) != 0) {
						pm1.show(jp, e.getX(), e.getY());
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}

		public void treeAddListener() {
			jTree.addMouseListener(new MouseListener() {

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

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					TreePath path = jTree.getSelectionPath();
					if (path != null) {
						String pathStr = path.toString().replaceFirst("C", "C:").replace("[", "").replace("]", "")
								.replace(",", "\\").replace(" ", "");
						jtf.setText(pathStr);
						jp.removeAll();
						addLabel(fOp.getFATs(pathStr), pathStr);
						jp.updateUI();
					}
				}
			});
		}

		public void menuItemAddListener() {
			jmi1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int flag = fOp.createFile(jp4, jp, jtf.getText(), nodeMap);
					if (flag == -1)
						JOptionPane.showMessageDialog(jp, "磁盘已满，无法创建文件", "错误", JOptionPane.WARNING_MESSAGE,
								new ImageIcon(getClass().getResource("/images/错误.png")));
					else {
						jp.removeAll();
						addLabel(fOp.getFATs(jtf.getText()), jtf.getText());
						jp.updateUI();
						jp4.removeAll();
						initDiskChange(jp4);
						jp4 = diskChange.disk();
						jp4.updateUI();
					}
				}
			});

			jmi2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int flag = fOp.createFolder(jp4, jp, jtf.getText(), nodeMap);
					if (flag == -1)
						JOptionPane.showMessageDialog(jp, "磁盘已满，无法创建文件夹", "错误", JOptionPane.ERROR_MESSAGE,
								new ImageIcon(getClass().getResource("/images/错误.png")));
					else {
						FAT folderFAT = fOp.getFAT(flag);
						DefaultMutableTreeNode node = new DefaultMutableTreeNode((Folder) folderFAT.getObject());
						nodeMap.put(jtf.getText() + "\\" + ((Folder) (folderFAT.getObject())).getFoldername(), node);
						DefaultMutableTreeNode parent = nodeMap.get(jtf.getText());
//						System.out.println(node+","+parent);
						parent.add(node);
						jTree.updateUI();
						jp.removeAll();
						addLabel(fOp.getFATs(jtf.getText()), jtf.getText());
						jp.updateUI();
						jp4.removeAll();
						initDiskChange(jp4);
						jp4 = diskChange.disk();
						jp4.updateUI();
					}
				}
			});

			// 只读打开文件
			mi0.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (fatList.get(select).getProperty() == "File") {
						if (fOp.getOpenFile().getLength() < 5) {
							if (fOp.checkOpenFile(fatList.get(select))) {
								JOptionPane.showMessageDialog(jp, "文件已经打开", "提示", JOptionPane.WARNING_MESSAGE,
										new ImageIcon(getClass().getResource("/images/感叹号.png")));
								return;
							}
							((File)fatList.get(select).getObject()).setReadOnly(true);
							fOp.addOpenFile(fatList.get(select), 0);
							oft.initData();
							jTable.updateUI();
							new openFileFrame_rom(oft,fatList.get(select), fOp, jTable);
						} else
							JOptionPane.showMessageDialog(jp, "最多只能打开5个文件，已达上限", "提示", JOptionPane.WARNING_MESSAGE,
									new ImageIcon(getClass().getResource("/images/感叹号.png")));
					} else {
						Folder folder = (Folder) fatList.get(select).getObject();
						String path = folder.getPath() + "\\" + folder.getFoldername();
						jp.removeAll();
						addLabel(fOp.getFATs(path), path);
						jp.updateUI();
						jtf.setText(path);
					}
				}
			});

			// 创建文件
			mi1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int flag = fOp.createFile(jp4, jp, jtf.getText(), nodeMap);
					if (flag == -1)
						JOptionPane.showMessageDialog(jp, "磁盘已满，无法创建文件", "错误", JOptionPane.WARNING_MESSAGE,
								new ImageIcon(getClass().getResource("/images/错误.png")));
					else {
						jp.removeAll();
						addLabel(fOp.getFATs(jtf.getText()), jtf.getText());
						jp.updateUI();
						jp4.removeAll();
						initDiskChange(jp4);
						jp4 = diskChange.disk();
						jp4.updateUI();
					}
				}
			});

			// 创建文件夹
			mi2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int flag = fOp.createFolder(jp4, jp, jtf.getText(), nodeMap);
					if (flag == -1)
						JOptionPane.showMessageDialog(jp, "磁盘已满，无法创建文件夹", "错误", JOptionPane.ERROR_MESSAGE,
								new ImageIcon(getClass().getResource("/images/错误.png")));
					else {
						FAT folderFAT = fOp.getFAT(flag);
						DefaultMutableTreeNode node = new DefaultMutableTreeNode((Folder) folderFAT.getObject());
						nodeMap.put(jtf.getText() + "\\" + ((Folder) (folderFAT.getObject())).getFoldername(), node);
						DefaultMutableTreeNode parent = nodeMap.get(jtf.getText());
//						System.out.println(node+","+parent);
						parent.add(node);
						jTree.updateUI();
						jp.removeAll();
						addLabel(fOp.getFATs(jtf.getText()), jtf.getText());
						jp.updateUI();
						jp4.removeAll();
						initDiskChange(jp4);
						jp4 = diskChange.disk();
						jp4.updateUI();
					}
				}
			});

			// 读写打开文件
			mi3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (fatList.get(select).getProperty() == "File") {
						if (fOp.getOpenFile().getLength() < 5) {
							if (fOp.checkOpenFile(fatList.get(select))) {
								JOptionPane.showMessageDialog(jp, "文件已经打开", "提示", JOptionPane.WARNING_MESSAGE,
										new ImageIcon(getClass().getResource("/images/感叹号.png")));
								return;
							}
							((File)fatList.get(select).getObject()).setReadOnly(false);
							fOp.addOpenFile(fatList.get(select), 0);
							oft.initData();
							jTable.updateUI();
							new openFileFrame_rw(oft,fatList.get(select), fOp, jp4, jTable);
						} else
							JOptionPane.showMessageDialog(jp, "最多只能打开5个文件，已达上限", "提示", JOptionPane.WARNING_MESSAGE,
									new ImageIcon(getClass().getResource("/images/感叹号.png")));
					}
				}
			});

			// 重命名文件
			mi4.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new rename(jp, fatList.get(select), nodeMap, fOp);
					jp.removeAll();
					addLabel(fOp.getFATs(jtf.getText()), jtf.getText());
					jp.updateUI();
				}
			});

			// 删除文件
			mi5.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int i = JOptionPane.showConfirmDialog(jp, "确定删除？", "确认", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/images/问号.png")));
					if (i == 0) {
						fOp.delete(jp, fatList.get(select), nodeMap);
						fOp.deleteFolderItem(jtf.getText(), nodeMap);
						jTree.updateUI();
						jp.removeAll();
						addLabel(fOp.getFATs(jtf.getText()), jtf.getText());
						jp.updateUI();
						jp4.removeAll();
						initDiskChange(jp4);
						jp4 = diskChange.disk();
						jp4.updateUI();
					}
				}
			});

			// 文件属性
			mi6.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new ShowProperty(fatList.get(select));
				}
			});

			// 打开文件夹
			mi7.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (fatList.get(select).getProperty() == "Folder") {
						Folder folder = (Folder) fatList.get(select).getObject();
						String path = folder.getPath() + "\\" + folder.getFoldername();
						jp.removeAll();
						addLabel(fOp.getFATs(path), path);
						jp.updateUI();
						jtf.setText(path);
					}
				}
			});

			// 重命名文件夹
			mi8.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new rename(jp, fatList.get(select), nodeMap, fOp);
					jp.removeAll();
					addLabel(fOp.getFATs(jtf.getText()), jtf.getText());
					jp.updateUI();
				}
			});

			// 删除文件夹
			mi9.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int i = JOptionPane.showConfirmDialog(jp, "确定删除？", "确认", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, new ImageIcon(getClass().getResource("/images/问号.png")));
					if (i == 0) {
						fOp.delete(jp, fatList.get(select), nodeMap);
						fOp.deleteFolderItem(jtf.getText(), nodeMap);
						jTree.updateUI();
						jp.removeAll();
						addLabel(fOp.getFATs(jtf.getText()), jtf.getText());
						jp.updateUI();
						jp4.removeAll();
						initDiskChange(jp4);
						jp4 = diskChange.disk();
						jp4.updateUI();
					}
				}
			});

			// 文件夹属性
			mi10.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new ShowProperty(fatList.get(select));
				}
			});
		}

		public void addLabel(List<FAT> fat, String path) {
			int isFile = 0;
			fatList = fat;
			int length = fat.size();
			jp.setPreferredSize(new Dimension(480, constant.getHeight(length)));
			labels = new Label[length];
			for (int i = 0; i < length; i++) {
				if (fat.get(i).getnextDiskNum() == 255) {
					if (fat.get(i).getProperty() == "File") {
						isFile = 0;
						labels[i] = new Label(0, ((File) fat.get(i).getObject()).getFileName());
					} else {
						isFile = 1;
						labels[i] = new Label(1, ((Folder) fat.get(i).getObject()).getFoldername());
					}

					if (labels[i].getIsFile() == 1) {
//						System.out.println(labels[i].getIsFile());
//						System.out.println("打开folder");
						labels[i].add(pm2);
					} else {
//						System.out.println(labels[i].getIsFile());
//						System.out.println("打开file");
						labels[i].add(pm3);
					}
					jp.add(labels[i]);
					labels[i].addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mousePressed(MouseEvent e) {
							for (int i = 0; i < length; i++) {
								if (e.getSource() == labels[i] && ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0)) {
									if (labels[i].getIsFile() == 1) {
										pm2.show(labels[i], e.getX(), e.getY());
//										System.out.println("打开folder1");
									} else if (labels[i].getIsFile() == 0) {
										pm3.show(labels[i], e.getX(), e.getY());
//										System.out.println("打开file1");
									}
								}
							}
						}

						@Override
						public void mouseExited(MouseEvent e) {
							for (int i = 0; i < length; i++)
								if (e.getSource() == labels[i]) {
									select = i;
									if (labels[i].getIsFile() == 0)
										labels[i].setIcon(new ImageIcon(getClass().getResource("/images/file.png")));
									else
										labels[i].setIcon(new ImageIcon(getClass().getResource("/images/folder.png")));
								}

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							for (int i = 0; i < length; i++)
								if (e.getSource() == labels[i]) {
									select = i;
									if (labels[i].getIsFile() == 0)
										labels[i].setIcon(new ImageIcon(getClass().getResource("/images/file1.png")));
									else
										labels[i].setIcon(new ImageIcon(getClass().getResource("/images/folder1.png")));
								}
						}

						@Override
						public void mouseClicked(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}
					});
				}
			}
		}
	}

}
