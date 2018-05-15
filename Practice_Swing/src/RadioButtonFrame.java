import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.awt.*;

public class RadioButtonFrame extends JFrame implements ActionListener{
	private JRadioButton small, medium, large;
	private JLabel text;
	private JPanel topPanel, sizePanel, resultPanel;
	
	public void MyFrame() {
		setTitle("라디오 버튼 테스트");
		setSize(300, 150); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topPanel = new JPanel();
		JLabel label = new JLabel("어떤 크기의 커피를 주문하시겠습니까?"); topPanel.add(label);
		add(topPanel, BorderLayout.NORTH);
		 
		sizePanel = new JPanel();
		small = new JRadioButton("Small Size"); 
		medium = new JRadioButton("Medium Size"); 
		large = new JRadioButton("Large Size");
		ButtonGroup size = new ButtonGroup(); 
		size.add(small);
		size.add(medium);
		size.add(large);
		small.addActionListener(this);
		medium.addActionListener(this); 
		large.addActionListener(this);
		sizePanel.add(small); 
		sizePanel.add(medium); 
		sizePanel.add(large);
		add(sizePanel, BorderLayout.CENTER);
		 
		resultPanel = new JPanel();
		text = new JLabel("크기가 선택되지 않았습니다.");
		text.setForeground(Color.red); resultPanel.add(text);
		add(resultPanel, BorderLayout.SOUTH); setVisible(true);
		}
			public void actionPerformed(ActionEvent e) {
			if (e.getSource() == small) { text.setText("Small 크기가 선택되었습니다.");
		}
		if (e.getSource() == medium) {
			text.setText("Medium 크기가 선택되었습니다."); }
		if (e.getSource() == large) { text.setText("Large 크기가 선택되었습니다.");
		} 
	}
		
}


