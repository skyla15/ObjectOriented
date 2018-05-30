// 1번 문제

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Clac implements ActionListener
{
	private JFrame frame = new JFrame("interest calculator");
	
	private JLabel inputValueLabel = new JLabel("원금 입력");
	private JTextField inputValueText = new JTextField(10);
	
	private JLabel inputPercentLabel = new JLabel("이율 입력");
	private JTextField inputPercentText = new JTextField(10);
	
	private JButton button = new JButton("변환");
	private JLabel result = new JLabel("결과");
	
	public Clac()
	{
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridy = 1;
		gbc.gridx = 1;
		frame.add(inputValueLabel, gbc);
		
		gbc.gridx = 2;
		frame.add(inputValueText, gbc);		
		
		
		gbc.gridy = 2;
		gbc.gridx = 1;
		frame.add(inputPercentLabel, gbc);
		
		gbc.gridx = 2;
		frame.add(inputPercentText, gbc);
		
		
		gbc.gridy = 3;
		frame.add(button, gbc);
		button.addActionListener(this);
		
		gbc.gridy = 4;
		frame.add(result, gbc);
		
		frame.setSize(400, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
		public void actionPerformed(ActionEvent e) {			
			/*
			 * e.getCommand 를 이용하면 switch case문 사용 
			 */
			// TODO Auto-generated method stub
			if(e.getSource() == button) {
				String inputValue = inputValueText.getText();
				System.out.println(inputValue);
				String interest = inputPercentText.getText();
				Double conversion = Double.parseDouble(inputValue)*Double.parseDouble(interest);
				
				result.setText("결과 : " + conversion);
			}
	}
	
		
	public static void main(String args[])
	{
		new Clac();
	}
}

//1번문제 여기 변환에 action listener 
