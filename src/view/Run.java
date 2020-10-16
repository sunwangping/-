package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;



public class Run extends JFrame{
	public static int n = 0;
	private JPanel jpSearch,jpMain,jp3,jp4,jp5;
	private JSplitPane jsp;
	private JMenuBar jmb;
	private JMenu jm1,jm2,jm3,jm4;
	private JMenuItem jmi1,jmi2,jmi3,jmi4;
	private Tree jtr;
	private JTextField jtf;
	private JLabel jl1,jl2,jl3;
	
	public Run(String title) {
		super(title);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		initMainFrame();
		creatJMenuBar();
		creatSearch();
		contentPane.add(jpMain,BorderLayout.CENTER);
		contentPane.add(jmb, BorderLayout.NORTH);
	}
	
	public void initMainFrame() {
//		jp3 = new JPanel();
//		jp4 = new JPanel();
//		jp4.setBackground(Color.white);
//		jp3.setPreferredSize(new Dimension(10,400));
//		jp4.setPreferredSize(new Dimension(300,400));
		jtr = new Tree();
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());
		jpMain.add(jtr,BorderLayout.WEST);
//		jpMain.add(jp3,BorderLayout.CENTER);
//		jpMain.add(jp4,BorderLayout.EAST);
	}	
	public void creatSearch() {
		jl1 = new JLabel("                   路径：");
		jtf = new JTextField("C");
		jtf.setPreferredSize(new Dimension(450, 20));
		jpSearch = new JPanel();
		jpSearch.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpSearch.setPreferredSize(new Dimension(1000, 30));
		jpSearch.add(jl1);
		jpSearch.add(jtf);
		jpMain.add(jpSearch,BorderLayout.NORTH);
	}
	public void creatJMenuBar() {
		jmb = new JMenuBar();
		jm1 = new JMenu("新建");
		jm2 = new JMenu("操作");
		jmi1 = new JMenuItem("新建文件");
		jmi2 = new JMenuItem("新建文件夹");
		jm3 = new JMenu("系统");
		jm4 = new JMenu("关于");
		jm1.add(jmi1);
		jm1.addSeparator();
		jm1.add(jmi2);
		
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
	}
	public class Tree extends JPanel{
		private JTree jTree;
		private DefaultMutableTreeNode rootNode;
		private JScrollPane jsp1, jsp2;
		private JSplitPane jsp;
		private JPanel jp;
		private JPopupMenu pm1, pm2;
		private JMenuItem mi1, mi2, mi3, mi4, mi5, mi6;
		private Images[] jL;
		public  Tree() {
			this.initMenuItem();
			this.initMenuItenByJLabel();
			this.initTreeFrame();
			this.jpAddListener();
			this.add(jsp);
		}
		
		public void initMenuItem(){
			pm1 = new JPopupMenu();
			mi1 = new JMenuItem("新建文件");
			mi2 = new JMenuItem("新建文件夹");
			pm1.add(mi1);
			pm1.addSeparator(); 
			pm1.add(mi2);
		}
		
		public void initMenuItenByJLabel(){
			pm2 = new JPopupMenu();
			mi3 = new JMenuItem("打开");
			mi4 = new JMenuItem("重命名");
			mi5 = new JMenuItem("删除");
			mi6 = new JMenuItem("属性");
			pm2.add(mi3);
			pm2.add(mi4);
			pm2.add(mi5);
			pm2.add(mi6);
		}

		public void initTreeFrame() {
			rootNode = new DefaultMutableTreeNode("C:");
			jTree = new JTree(rootNode);
			jsp1 = new JScrollPane(jTree);
			jsp1.setPreferredSize(new Dimension(200, 400));
			jp = new JPanel();
			jp.setLayout(new FlowLayout(FlowLayout.LEFT));
			jp.setBackground(Color.white);
			jp.add(pm1);
			jsp2 = new JScrollPane(jp);
			jsp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jsp2.setBackground(Color.white);
			jsp2.setPreferredSize(new Dimension(482, 400));
			jsp2.setViewportView(jp);
			jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jsp1,jsp2);
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
					if ((mods&InputEvent.BUTTON3_MASK) != 0){
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
        
		
	}
	

}
