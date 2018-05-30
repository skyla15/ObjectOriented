package pro2;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.math.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Different_Fonts extends JFrame {
	private JFrame frame = new JFrame("5 Different_Font");
	

	
	JLabel[] labelList = new JLabel[5];


	
	public Font chooseRandomFont(int random) {
		Font font;
		int size = (int)(Math.random()*30+10);

		switch(random) {
		case 1 : 
			font = new Font("Baskerville", Font.PLAIN, size);
			break;
		case 2:
			font = new Font("Big Caslon", Font.BOLD, size);
			break;
		case 3:
			font = new Font("Cochin", Font.ITALIC, size);
			break;
		case 4:
			font = new Font("Times", Font.PLAIN, size);
			break;
		case 5:
			font = new Font("Didot", Font.PLAIN, size);
			break;
		default :
			font = new Font("Copperplate", Font.PLAIN, size);
		}
		
		return font;
	}
	
	public Different_Fonts() {

		for(int i = 0 ; i < labelList.length ; i ++) {
			int random2 = (int)(Math.random()*5);
			Font font = chooseRandomFont(random2);
			labelList[i] = new JLabel("Hello World!");
			labelList[i].setFont(font);
		}
		
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setTitle("5 Different Fonts");
		
		gbc.gridy = 1;
		gbc.gridx = 1;
		frame.add(labelList[0], gbc);
		
		gbc.gridy = 2;
		frame.add(labelList[1], gbc);
		
		gbc.gridy = 3;
		frame.add(labelList[2], gbc);
		
		gbc.gridy = 4;
		frame.add(labelList[3], gbc);
		
		gbc.gridy = 5;
		frame.add(labelList[4], gbc);

		
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

	}
	

	
	public static void main(String[] args) {
		new Different_Fonts();
	}
	
}
