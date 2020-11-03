package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class introduce extends JFrame{
	
	private JFrame jf;
	public introduce(Component component) {
		this.jf =this;
		this.setTitle("œµÕ≥ΩÈ…‹");
		this.setSize(280, 330);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(component);
		this.setVisible(true);
	}
	
	
}
