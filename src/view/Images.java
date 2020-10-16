package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import constant.constant;


public class Images extends JLabel{
	
	private boolean type = false;
	
	public Images (boolean isFile, String text) {
		this.setVerticalTextPosition(JLabel.BOTTOM);
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setText(text);
		
		if (isFile){
			this.setIcon(new ImageIcon(getClass().getResource(constant.filePath)));
			this.type = true;
		} else {
			this.setIcon(new ImageIcon(getClass().getResource(constant.folderPath)));
			this.type = false;
		}
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}
	
	
			
}
