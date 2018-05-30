import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;


public class FileChooserTest extends JFrame implements ActionListener{
	JButton openButton, saveButton;
	JFileChooser fc;
	
	
	public FileChooserTest() {
		setTitle("File Chooser Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		
		fc = new JFileChooser();
		fc.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
		JLabel label = new JLabel("Testing for File Chooser Component Test");
		openButton = new JButton("Open File");
		openButton.addActionListener(this);
		
		saveButton = new JButton("Save File");
		saveButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(openButton);
		panel.add(saveButton);
		add(panel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == openButton) {
			fc.setDialogTitle("Open File");
			fc.setSelectedFile(new File("JTetirs.java"));
			int returnVal = fc.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
			}
		}
		
		else if(e.getSource() == saveButton) {
			fc.setDialogTitle("Save File");
			fc.setSelectedFile(new File("newTetris.java"));
			int returnVal = fc.showSaveDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
			}
		}
		
	}
	
	public static void main(String[] argx) {
		FileChooserTest frame = new FileChooserTest();
	}
}
