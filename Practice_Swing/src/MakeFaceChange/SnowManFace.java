package MakeFaceChange;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel extends JPanel implements ActionListener{
	JButton button;
	boolean clicked = false;
	public MyPanel() {
		button = new JButton("Make me Smile");
		button.addActionListener(this);
		add(button);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(clicked) {
			g.setColor(Color.YELLOW);
			g.fillOval(20, 30, 200, 200);
			g.setColor(Color.BLACK);
			g.drawArc(60,  80, 50, 50, 0, 180);
			g.drawArc(140,80,50,50,0,180);
			g.drawArc(140, 80, 50, 50, 0, 180);
			g.drawArc(75, 130, 100, 70, 180, 180);
			button.setText("I am happy");
		}
		else {
			g.setColor(Color.YELLOW);
			g.fillOval(20, 30, 200, 200);
			g.setColor(Color.BLACK);
			g.drawLine(60,  100, 100, 100);
			g.drawLine(140, 100, 180, 100);
			int[] xpoints = {80,90, 100, 110, 120, 130, 140, 150, 160};
			int[] ypoints = {180, 170, 180, 170, 180, 170, 180, 170, 180};
			g.drawPolyline(xpoints, ypoints, 9);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button) clicked = true;
		repaint(); // repaint 조나 중요
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

