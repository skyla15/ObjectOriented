package MoveBasicPaint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.PaintContext;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicPaint {
	public static void main(String[] args) {
		JFrame f= new JFrame("basic paint");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new MyPanel());
		f.setSize(300,200);;
		f.setVisible(true);
	}
}


/* 
 *  도형을 움직이게 하려면 여기에 소스 입력 ( 사진 참고 ) 
 * 사진에서 repaint가 빠질경우 어떻게 되는지 ?
 * => repaint를 해야지 그림이 그려진다
 * => repaint에 영역을 지정할 수도 있는데 어떻게 할 수 있는 지? 
 *    => pdf 의 그림 참고, 
 * */
class MyPanel extends JPanel{
	private int squareX = 50;
	private int squareY = 50;
	private int squareW = 20;
	private int squareH = 20;
	
	public MyPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				moveSquare(e.getX(), e.getY());
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				moveSquare(e.getX(), e.getY());
			}
		});		
	}
	
	private void moveSquare(int x, int y) {
		squareX = x;
		squareY = y;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("move rectangle", 10, 20);
		g.setColor(Color.RED);
		g.fillRect(squareX, squareY, squareW, squareH);
		g.setColor(Color.BLACK);
		g.drawRect(squareX, squareY, squareW, squareH);
		
	}
}

// 여기그림을 움직이도록 하려면 