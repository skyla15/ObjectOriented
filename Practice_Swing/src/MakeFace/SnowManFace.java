package MakeFace;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.fillOval(20, 30, 200, 200);
		g.setColor(Color.BLACK);
		g.drawArc(60, 80, 50, 50, 0, 180); // -180 ) ; // left eye
		g.drawArc(140,  80,  50,  50, 0, 180);
		g.drawArc(75, 130,  100, 70, 180, 180);
		
	}
}

public class SnowManFace extends JFrame{
	public SnowManFace() {
		setSize(280, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("snow man face");
		setVisible(true);
		add(new MyPanel());
	}
	
	public static void main(String[] argx) {
		SnowManFace s = new SnowManFace();
		s.setVisible(true);
	}
}

// 버튼 이벤트를 넣어서 얼굴 바뀌게 만들어보기 

