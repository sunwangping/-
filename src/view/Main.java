package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {

	private static void createGUI()
	{
		
		JFrame frame = new Run("ģ������ļ�ϵͳ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 600);
		frame.setLocation(200, 100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		
	}
	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				createGUI();
			}
		});

	}

}
