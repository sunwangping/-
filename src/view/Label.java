package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Label extends JLabel{
	
	private int isFile;
	
	public Label(int isFile, String text) {
		this.setText(text);
		this.setVerticalTextPosition(JLabel.BOTTOM);
		this.setHorizontalTextPosition(JLabel.CENTER);
		
		if(isFile == 0) {
			this.isFile = 0;
			this.setIcon(new ImageIcon(getClass().getResource("/images/file.png")));
		}
		else {
			this.isFile = 1;
			this.setIcon(new ImageIcon(getClass().getResource("/images/folder.png")));
		}
	}

	public int getIsFile() {
		return isFile;
	}

	public void setIsFile(int isFile) {
		this.isFile = isFile;
	}
}
