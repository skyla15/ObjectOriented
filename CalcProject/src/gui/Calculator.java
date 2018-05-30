package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;


public class Calculator
{
	final static int WIDTH_SIZE = 600;
	final static int HEIGHT_SIZE = 600;
	
	private static int calcState = 0; //0:basic; 1:engineering
	
	private JFrame frame = new JFrame("Calculator");
	private JPanel normalCalculator2 = new JPanel();
	private JPanel normalCalculator = new JPanel();
	
	private JPanel northPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel cardPanel = new JPanel();
	private CardLayout card = new CardLayout();
	private JPanel centerBasicPanel = new JPanel();
	private JPanel centerEngineeringPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	
	private String code = "";
	private Stack<String> codeStack = new Stack<String>();
	private Stack<String> exprStack = new Stack<String>();
	private JLabel exprStackRepLabel = new JLabel();
	private JLabel exprStackLabel[] = new JLabel[5];
	private JLabel expressionLabel;
	private JTextField resultWindow;
	private JLabel copyRightLabel = new JLabel("20XX11XXXX - who\'s");
	
	final String[] opGeneralSet = {"save", "load"};	
	final String[] opSet = {"C", "<", "%", "+", "-", "*", "/", "^", ".", "="};
	final String[] opEngSet = {"log", "exp", "sin", "cos", "tan"};
	private JButton opGeneralButtons[] = new JButton[opGeneralSet.length];
	private JButton opButtons[] = new JButton[opSet.length];
	private JButton opButtons2[] = new JButton[opSet.length];
	private JButton opEngButtons[] = new JButton[opEngSet.length];
	private JButton numberButtons[] = new JButton[10];
	private JButton numberButtons2[] = new JButton[10];

	class CalcListener implements ActionListener {
		ArrayList<String> postfix;
		ArrayList<Integer> state;
		Stack<String> opStack;
		boolean isDecimal(char x) {
			return '0' <= x && x <= '9';
		}
		boolean isUnaryOp(char x) {
			if(x == '<' || x == 'l' || x == 's' || x == 'c' || x == 't') return true;
			return false;
		}
		private String solve(String str) {
			postfix = new ArrayList<String>();
			state = new ArrayList<Integer>();
			opStack = new Stack<String>();
			
			for(int i=0;i<str.length();i++) {
				int from = i, to = i;

				char s = str.charAt(to);
				if(isDecimal(s)) {
					//���� �ν�
					while(isDecimal(s) || s == '.') { 
						to++;
						if(to >= str.length()) break;
						s = str.charAt(to); 
					}
					i = to-1;
					postfix.add(str.substring(from, to));
					state.add(0);
				}
				else {
					//������ �ν�
					char op = str.charAt(i);
					if(opStack.size() == 0)
						opStack.push(""+op);
					else if(op == '+' || op == '-' || isUnaryOp(op)) { 
						//�켱���� ����
						while(!opStack.empty()) {
							String t = opStack.pop();
							postfix.add(t);
							state.add(1);
						}
						opStack.push("" + op);
					}
					else { 
						//�켱���� ����
						while(!opStack.empty()) {
							char op_top = opStack.peek().charAt(0);
							if(op_top == '+' || op_top == '-')
								break;
							String t = opStack.pop();
							postfix.add(t);
							state.add(1);
						}
						opStack.push("" + op);						
					}
				}
			}
			while(!opStack.empty()) {
				String t = opStack.pop();
				postfix.add(t);
				state.add(1);
			}
			
			Stack<Double> res = new Stack<Double>();
			for(int i=0;i<postfix.size();i++) {
				String s = postfix.get(i);
				if(state.get(i) == 0)
					res.push(Double.parseDouble(s));
				else {
					char op = s.charAt(0);
					double r1 = 0, r2 = 0;
					r1 = res.pop();
					if(op == '<') // unary
						res.push(Math.sqrt(r1));
					else if(op == 'l')
						res.push(Math.log10(r1));
					else if(op == 's')
						res.push(Math.sin(r1));
					else if(op == 'c')
						res.push(Math.cos(r1));
					else if(op == 't')
						res.push(Math.tan(r1));

					else { //binary
						r2 = res.pop();
						if(op == '+')
							res.push(r1+r2);
						if(op == '-')
							res.push(r2-r1);
						if(op == '*')
							res.push(r1*r2);
						if(op == '/')
							res.push(r2/r1);
						if(op == '^')
							res.push(Math.pow(r2, r1));
						if(op == 'e')
							res.push(r2 * Math.pow(10, r1));
					}
				}
			}
			
			return String.valueOf(res.peek());
		}
		void refreshMemoryStack() {
			for(int i=0;i<exprStack.size();i++) {
				String t = exprStack.get(i);
				exprStackLabel[i].setText(t + "=" + solve(codeStack.get(i)));
			}
			for(int i=exprStack.size(); i<5;i++)
				exprStackLabel[i].setText("");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton b = (JButton)e.getSource();
			String name = b.getText();
			if(name.equals("save")) {
				if(codeStack.size() < 5) {
					codeStack.push(code);
					exprStack.push(expressionLabel.getText());
					refreshMemoryStack();
				}
			}
			else if(name.equals("load")) {
				if(!codeStack.empty()) {
					code = codeStack.pop();
					expressionLabel.setText(exprStack.pop());
					resultWindow.setText(solve(code));
					refreshMemoryStack();
				}
			}
			else if(name.equals("C")) {
				code = "";
				expressionLabel.setText(" ");
			}
			else if(name.equals("=")) {
				resultWindow.setText(solve(code));
			}
			else if(name.equals("%")) {
				int i = 0;
				for(i=code.length()-1;i>=0;i--) {
					if(!(isDecimal(code.charAt(i)) || code.charAt(i)=='.'))
						break;
				}
				char op = code.charAt(i);
				double solval = Double.parseDouble(solve(code.substring(0,i)));
				double perval = Double.parseDouble(solve(code.substring(i+1,code.length())));
				double res = 0;
				if(op == '+')
					res = solval + solval * perval / 100.0;
				else if(op == '-')
					res = solval - solval * perval / 100.0;
				else if(op == '*')
					res = solval * perval / 100.0;
				else if(op == '/')
					res = solval * 100 / perval;
					
				resultWindow.setText(res + "");				
			}
			else {
				for(int i=0;i<=9;i++)
					if(b.getText().equals(""+i)) {
						expressionLabel.setText(expressionLabel.getText()+i);
						code += "" + i;
					}
				for(int i=0;i<opButtons.length;i++)
					if(b.getText().equals(""+opSet[i])) {
						if(opSet[i].equals("<"))
							expressionLabel.setText("<(" + expressionLabel.getText() + ")");
						else
							expressionLabel.setText(expressionLabel.getText()+opSet[i]);
						code += opSet[i];
						//root: r
					}
				for(int i=0;i<opEngButtons.length;i++)
					if(b.getText().equals(""+opEngSet[i])) {
						if(i == 1)
							expressionLabel.setText(expressionLabel.getText() + "e+");
						else
							expressionLabel.setText(opEngSet[i] + "(" + expressionLabel.getText() + ")");
						code += opEngSet[i].charAt(0); 
						//log:l; exp:e; sin:s; cos:c; tan:t;
					}
			}
		}	
	}

	private void addToPanel(JPanel p, GridBagConstraints gbc, Component c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		p.add(c, gbc);
	}
	@SuppressWarnings("unused")
	public Calculator()
	{
		frame.setLayout(new CardLayout());
		
		// normal calculator
		NORMAL_CALC:
		{
			normalCalculator.setLayout(new BorderLayout());
			normalCalculator2.setLayout(new BorderLayout());
			
			
			NORTH:
			{
				northPanel.setLayout(new GridBagLayout());
				GridBagConstraints layoutRule = new GridBagConstraints();
				JButton basicButton = new JButton("basic");
				JButton engineeringButton = new JButton("engineering");
				JButton programmerButton = new JButton("---------");
				JButton matrixButton = new JButton("-------");
				ActionListener basicL = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						card.show(cardPanel, "basic");
						calcState = 0;
					}
				};
				ActionListener engineeringL = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						card.show(cardPanel, "engineering");
						calcState = 1;
					}
				};
				basicButton.addActionListener(basicL);
				engineeringButton.addActionListener(engineeringL);

				layoutRule.fill = GridBagConstraints.BOTH;
				layoutRule.weightx = 1.0;
				layoutRule.weighty = 1.0;

				addToPanel(northPanel, layoutRule, basicButton, 0, 1, 1, 1);
				addToPanel(northPanel, layoutRule, engineeringButton, 1, 1, 1, 1);
				addToPanel(northPanel, layoutRule, programmerButton, 2, 1, 1, 1);
				addToPanel(northPanel, layoutRule, matrixButton, 3, 1, 1, 1);
								
				layoutRule.gridwidth = 4;
				layoutRule.gridheight = 1;
				layoutRule.fill = GridBagConstraints.HORIZONTAL;
				
				JLabel stringWindow = new JLabel("Title");
				expressionLabel = new JLabel(" ");
				resultWindow = new JTextField(20);
				
				layoutRule.gridx = 0;
				layoutRule.gridy = 0;
				northPanel.add(stringWindow, layoutRule);
				
				layoutRule.gridy = 2;
				northPanel.add(expressionLabel, layoutRule);

				layoutRule.gridy = 3;
				northPanel.add(resultWindow, layoutRule);
				
			}
			EAST:
			{
				eastPanel.setLayout(new GridBagLayout());
				GridBagConstraints layoutRule = new GridBagConstraints();
				layoutRule.fill = GridBagConstraints.HORIZONTAL;
				layoutRule.weightx = 1.0;
				layoutRule.weighty = 1.0;
				exprStackRepLabel = new JLabel("---Memory Stack---");
				addToPanel(eastPanel, layoutRule, exprStackRepLabel, 0, 0, 3, 1);
				for(int i=0;i<5;i++) {
					exprStackLabel[i] = new JLabel("");
					addToPanel(eastPanel, layoutRule, exprStackLabel[i], 0, i+1, 3, 1);
				}
				for(int i=0;i<opGeneralButtons.length;i++) {
					opGeneralButtons[i] = new JButton(opGeneralSet[i]);
					addToPanel(eastPanel, layoutRule, opGeneralButtons[i], i, 6, 1, 1);
					CalcListener c = new CalcListener();
					opGeneralButtons[i].addActionListener(c);
				}

			}
		
			SOUTH:
			{
				bottomPanel.add(copyRightLabel);
			}
		
		
			CENTER:
			{
				final int buttonWidth = 4;
				final int buttonHeight = 5;
				
				centerBasicPanel.setLayout(new GridBagLayout());
				centerEngineeringPanel.setLayout(new GridBagLayout());
				GridBagConstraints layoutRule = new GridBagConstraints();
				
				layoutRule.gridwidth = 1;
				layoutRule.gridheight = 1;
				layoutRule.weightx = 0.2;
				layoutRule.weighty = 0.2;
				
				layoutRule.fill = GridBagConstraints.CENTER;

				for (int i =  0; i < opButtons.length; i++) {
					opButtons[i] = new JButton(opSet[i]);
					opButtons2[i] = new JButton(opSet[i]);
					CalcListener c = new CalcListener();
					opButtons[i].addActionListener(c);
					opButtons2[i].addActionListener(c);
				}
				for(int i=0; i<opEngButtons.length; i++) {
					opEngButtons[i] = new JButton(opEngSet[i]);
					CalcListener c = new CalcListener();
					opEngButtons[i].addActionListener(c);
				}
				
				for (int i = 0; i < 10; i++) {
					numberButtons[i] = new JButton(String.valueOf(i));
					numberButtons2[i] = new JButton(String.valueOf(i));
					CalcListener c = new CalcListener();
					numberButtons[i].addActionListener(c);
					numberButtons2[i].addActionListener(c);
				}
				
				for (int i = 0; i < buttonHeight - 1; i++)
				{
					layoutRule.gridy = i;
					
					if (i >= buttonHeight - 4)
					{
						final int numbering = buttonHeight - i - 1;
						
						for (int j = 0; j < 3; j++)
						{
							layoutRule.gridx = j;
							centerBasicPanel.add(numberButtons[numbering * 3 - 2 + j], layoutRule);
							centerEngineeringPanel.add(numberButtons2[numbering * 3 - 2 + j], layoutRule);
						}
						
						for (int j = 3; j < buttonWidth; j++)
						{
							layoutRule.gridx = j;
							centerBasicPanel.add(opButtons[j + (i * (buttonWidth - 3))], layoutRule);
							centerEngineeringPanel.add(opButtons2[j + (i * (buttonWidth - 3))], layoutRule);
						}
					}
					
					else
					{
						for (int j = 0; j < buttonWidth; j++)
						{
							layoutRule.gridx = j;
							centerBasicPanel.add(opButtons[j + (i * buttonWidth)], layoutRule);
							centerEngineeringPanel.add(opButtons2[j + (i * buttonWidth)], layoutRule);
						}
					}
				}
				
				layoutRule.gridy = buttonHeight - 1;
				
				layoutRule.gridx = 0;
				centerBasicPanel.add(opButtons[opButtons.length - buttonWidth + 1], layoutRule);
				centerEngineeringPanel.add(opButtons2[opButtons2.length - buttonWidth + 1], layoutRule);
				
				layoutRule.gridx = 1;
				centerBasicPanel.add(numberButtons[0], layoutRule);
				centerEngineeringPanel.add(numberButtons2[0], layoutRule);
				
				for (int i = opButtons.length - buttonWidth + 2; i < opButtons.length; i++)
				{
					layoutRule.gridx = i - opButtons.length + buttonWidth;
					centerBasicPanel.add(opButtons[i], layoutRule);
					centerEngineeringPanel.add(opButtons2[i], layoutRule);
				}

				for(int i=0;i<opEngButtons.length;i++) {
					addToPanel(centerEngineeringPanel, layoutRule, opEngButtons[i], i, 6, 1, 1);
				}
				

				cardPanel.setLayout(card);
				cardPanel.add("basic", centerBasicPanel);
				cardPanel.add("engineering", centerEngineeringPanel);
				card.first(cardPanel);
			}
		
			normalCalculator.add(northPanel, BorderLayout.NORTH);
			normalCalculator.add(cardPanel, BorderLayout.CENTER);
			normalCalculator.add(bottomPanel, BorderLayout.SOUTH);
			
			normalCalculator2.add(normalCalculator, BorderLayout.CENTER);
			normalCalculator2.add(eastPanel, BorderLayout.EAST);
			
		}
		
		frame.add(normalCalculator2, "normal");
			
		frame.setSize(WIDTH_SIZE, HEIGHT_SIZE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[])
	{
		new Calculator();
	}
}