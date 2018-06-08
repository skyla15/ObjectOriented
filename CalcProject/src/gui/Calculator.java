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
    // �߰�
    private JPanel normalCalculator3 = new JPanel();
    private JPanel normalCalculator2 = new JPanel();
    private JPanel normalCalculator = new JPanel();
    
    private JPanel northPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    
    private JPanel cardPanel = new JPanel();
    private CardLayout card = new CardLayout();
    
    private JPanel centerBasicPanel = new JPanel();
    private JPanel centerEngineeringPanel = new JPanel();
    private JPanel centerProgrammerPanel = new JPanel(); // ���α׷��ӿ�
    private JPanel eastPanel = new JPanel();
    
    private String code = "";
    private Stack<String> codeStack = new Stack<String>();
    private Stack<String> exprStack = new Stack<String>();
    private JLabel exprStackRepLabel = new JLabel();
    private JLabel exprStackLabel[] = new JLabel[5];
    private JLabel expressionLabel;
    private JTextField resultWindow;
    private JLabel copyRightLabel = new JLabel("2013112130 - Jaeyeop's");
    
    final String[] opGeneralSet = {"save", "load"};
    final String[] opSet = {"C", "<", "%", "+", "-", "*", "/", "^", ".", "="};
    final String[] opEngSet = {"log", "exp", "sin", "cos", "tan"};
    
    //���α׷��ӿ� ����
    final String[] opLogicSet = {"and", "xor", "or", "not"};
    final String[] opBinSet = { "Uno", "Dos", "Bin", "Hex", "Oct"};
    private boolean checkLogic;
    private boolean checkBin;
    
    private JButton opGeneralButtons[] = new JButton[opGeneralSet.length];
    private JButton opButtons[] = new JButton[opSet.length];
    private JButton opButtons2[] = new JButton[opSet.length];
    private JButton opButtons3[] = new JButton[opSet.length];
    
    //���п� ����
    private JButton opEngButtons[] = new JButton[opEngSet.length];
    
    //���α׷��ӿ� ����
    private JButton opLogicButtons[] = new JButton[opLogicSet.length];
    private JButton opBinButtons[] = new JButton[opBinSet.length];
    
    //�� �뵵���� ���� ��ư ����
    private JButton numberButtons[] = new JButton[10]; // �Ϲ�
    private JButton numberButtons2[] = new JButton[10]; // ����
    private JButton numberButtons3[] = new JButton[10]; // ���α׷���
    
    //Calculator Listener
    class CalcListener implements ActionListener {
        ArrayList<String> postfix;
        ArrayList<Integer> state;
        Stack<String> opStack;
        
        boolean isDecimal(char x) {
            return '0' <= x && x <= '9';
        }
        
        boolean isUnaryOp(char x) {
            if(x == '<' || x == 'l' || x == 's' || x == 'c' || x == 't' || x == 'B' || x == 'O' || x == 'H' || x =='U' || x == 'D' || x == '%') return true;
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
                    // �Էµ� ���� ���� �ν�
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
            int logicRes; // ���� ����� ����
            String logicResult; // ���� ����� ����
            
            for(int i=0;i<postfix.size();i++) {
                String s = postfix.get(i);
                if(state.get(i) == 0) // state�� 0 �ϰ�� ��, �Էµ� ���ڸ� ���������� ��ȯ�Ͽ� ����
                    res.push(Double.parseDouble(s));
                else {
                    char op = s.charAt(0);
                    double r1 = 0, r2 = 0;
                    
                    r1 = res.pop();
                    
                    if(op == '<') // unary
                        res.push(Math.sqrt(r1));
                    else if(op == 'l') // log
                        res.push(Math.log10(r1));
                    else if(op == 's') {// sin
                        res.push(Math.sin(Math.toRadians(r1%360.0)*1)); //입력된 각도값을 라디안값으로 변환 후 연산
                        System.out.println(r1%360);
                    }
                    else if(op == 'c') // cos
                        res.push(Math.cos(Math.toRadians(r1%360.0)*1));
                    else if(op == 't') // tan
                        res.push(Math.tan(Math.toRadians(r1%360.0)*1));
                    else if(op == '%')
                        res.push(r1 * 0.01);
                    
                    // Binary ���� �߰�
                    else if(op == 'B') {//to Binar
                        resultWindow.setText(convertTo_Bin(r1));
                        res.push(Double.parseDouble(convertTo_Bin(r1)));
                        checkBin = true;
                        
                        return null;
                    }
                    else if(op == 'H') { //to Hex
                        resultWindow.setText(convertTo_Hex(r1));
                        res.push(Double.parseDouble(convertTo_Hex(r1)));
                        
                        checkBin = true;
                        return null;
                    }
                    else if(op == 'O') { //to Octal
                        resultWindow.setText(convertTo_Oct(r1));
                        res.push(Double.parseDouble(convertTo_Oct(r1)));
                        
                        checkBin = true;
                        return null;
                    }
                    else if(op == 'U') { //to 1's complement
                        resultWindow.setText(convertTo_1s_Complement(r1));
                        res.push(Double.parseDouble(convertTo_1s_Complement(r1)));
                        
                        checkBin = true;
                        return null;
                    }
                    else if(op == 'D') {//to 2's complement
                        resultWindow.setText(convertTo_2s_Complement(r1));
                        res.push(Double.parseDouble(convertTo_2s_Complement(r1)));
                        
                        checkBin = true;
                        return null;
                    }
                    else if(op == 'n') { //not
                        logicRes = ~(int)r1;
                        logicResult = Integer.toBinaryString(logicRes);
                        resultWindow.setText(logicResult);
                        checkLogic = true;
                        return null;
                    }
                    
                    else { //binary, ���׿���
                        
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
                        // Logic �߰�
                        if(op == 'a') {
                            logicRes = (int)r1 & (int)r2;
                            logicResult = Integer.toBinaryString(logicRes);
                            res.push(Double.parseDouble(logicResult));
                            checkLogic = true;
                        }
                        if(op == 'x') {
                            logicRes = (int)r1 ^ (int)r2;
                            logicResult = Integer.toBinaryString(logicRes);
                            res.push(Double.parseDouble(logicResult));
                            checkLogic = true;
                        }
                        if(op == 'o') {
                            logicRes = (int)r1 | (int)r2;
                            logicResult = Integer.toBinaryString(logicRes);
                            res.push(Double.parseDouble(logicResult));
                            checkLogic = true;
                        }
                        
                    }
                }
            }
            
            return String.valueOf(res.peek());
        }
        
        
        //2�� ��ȯ
        public String convertTo_Bin(double input) {
            int x = (int)input;
            String res = "";
            ArrayList binary = new ArrayList();
            while(x != 0) {
                binary.add(x%2);
                x = x/2;
            }
            for(int i = binary.size() ; i > 0 ; i--) {
                res += binary.get(i-1);
            }
            return res;
        }
        
        //8�� ��ȯ
        public String convertTo_Oct(double input) {
            int x = (int)input;
            String res = "";
            ArrayList octal = new ArrayList();
            while(x != 0) {
                octal.add(x%8);
                x = x/8;
            }
            
            for(int i = octal.size() ; i > 0 ; i--) {
                res += octal.get(i-1);
            }
            return res;
        }
        
        //16���� ��ȯ
        public String convertTo_Hex(double input) {
            int x = (int)input;
            String res = "";
            res = String.format("%x",  x);
            return res;
        }
        
        //1�� ���� ��ȯ
        public String convertTo_1s_Complement(double input) {
            int x = (int)input;
            String res ="";
            res += Integer.toBinaryString(~x);
            return res;
        }
        
        //2�� ���� ��ȯ
        public String convertTo_2s_Complement(double input) {
            int x = (int)input;
            String res = "";
            x = ~x + 1;
            res += Integer.toBinaryString(x);
            System.out.println(res);
            return res;
        }
        
        
        
        // ���� ���� �߰�
        
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
                resultWindow.setText(" ");
            }
            
            else if(name.equals("=")) {
                resultWindow.setText(solve(code));
            }
            
            else if(checkLogic == true || checkBin == true) { // ���α׷��ӿ� ����� �ʿ�
                expressionLabel.setText(" ");
                resultWindow.setText(" ");
                code = "";
                checkLogic = false;
                checkBin = false;
            }
            
            //
            //            else if(name.equals("%")) {
            //                int i = 0;
            //                for(i=code.length()-1;i>=0;i--) {
            //                    if(!(isDecimal(code.charAt(i)) || code.charAt(i)=='.'))
            //                        break;
            //                }
            //                char op = code.charAt(i);
            //                double solval = Double.parseDouble(solve(code.substring(0,i)));
            //                double perval = Double.parseDouble(solve(code.substring(i+1,code.length())));
            //                double res = 0;
            //                if(op == '+')
            //                    res = solval + solval * perval / 100.0;
            //                else if(op == '-')
            //                    res = solval - solval * perval / 100.0;
            //                else if(op == '*')
            //                    res = solval * perval / 100.0;
            //                else if(op == '/')
            //                    res = solval * 100 / perval;
            //
            //                resultWindow.setText(res + "");
            //            }
            
            else {
                for(int i=0;i<=9;i++) {
                    if(b.getText().equals(""+i)) {
                        expressionLabel.setText(expressionLabel.getText()+i);
                        code += "" + i;
                    }
                }
                for(int i=0;i<opButtons.length;i++)
                    if(b.getText().equals(""+opSet[i])) {
                        if(opSet[i].equals("%")) {
                            expressionLabel.setText(expressionLabel.getText() + opSet[i]);
                        }
                        else if(opSet[i].equals("<"))
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
                
                //logic button
                for(int i = 0 ; i< opLogicButtons.length ; i++)
                    if(b.getText().equals(""+opLogicSet[i])) {
                        if(name.equals("not")) {
                            expressionLabel.setText("~" + expressionLabel.getText());
                            code += opLogicSet[i].charAt(0);
                            solve(code);
                        }
                        else {
                            expressionLabel.setText(expressionLabel.getText() + opLogicSet[i]);
                            code += opLogicSet[i].charAt(0);
                        }
                        
                    }
                
            }
            
            //
            
            for(int i = 0 ; i < opBinSet.length ; i++) {
                if(b.getText().equals(""+opBinSet[i])) {
                    if(b.getText().equals("Uno")) {
                        expressionLabel.setText("convert to 1's complement :" + expressionLabel.getText());
                        code += opBinSet[i].charAt(0);
                        solve(code);
                    }
                    else if(b.getText().equals("Dos")) {
                        expressionLabel.setText("convert to 2's complement :" + expressionLabel.getText());
                        code += opBinSet[i].charAt(0);
                        solve(code);
                    }
                    else{
                        expressionLabel.setText(opBinSet[i] + expressionLabel.getText());
                        code += opBinSet[i].charAt(0);
                        solve(code);
                    }
                    
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
            // ���α׷��ӿ� �߰�
            normalCalculator3.setLayout(new BorderLayout());
            
            
        NORTH:
            {
                northPanel.setLayout(new GridBagLayout());
                GridBagConstraints layoutRule = new GridBagConstraints();
                JButton basicButton = new JButton("basic");
                JButton engineeringButton = new JButton("engineering");
                JButton programmerButton = new JButton("programmer"); // ���α׷��ӿ�
                JButton matrixButton = new JButton("-------");
                
                // Basic Calculator Show
                ActionListener basicL = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        card.show(cardPanel, "basic");
                        resultWindow.setText("");
                        expressionLabel.setText("");
                        opGeneralButtons[0].setEnabled(true);
                        opGeneralButtons[1].setEnabled(true);
                        
                        calcState = 0;
                    }
                };
                
                // Engineering Calculator Show
                ActionListener engineeringL = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        card.show(cardPanel, "engineering");
                        resultWindow.setText("");
                        expressionLabel.setText("");
                        calcState = 1;
                        opGeneralButtons[0].setEnabled(true);
                        opGeneralButtons[1].setEnabled(true);
                    }
                };
                
                // dProgrammer Calculator Show
                ActionListener programmerL = new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        card.show(cardPanel, "programmer");
                        resultWindow.setText("");
                        expressionLabel.setText("");
                        opGeneralButtons[0].setEnabled(false);
                        opGeneralButtons[1].setEnabled(false);
                        
                        calcState = 2;
                    }
                };
                
                
                basicButton.addActionListener(basicL);
                engineeringButton.addActionListener(engineeringL);
                programmerButton.addActionListener(programmerL);// ���α׷��ӿ�
                
                layoutRule.fill = GridBagConstraints.BOTH;
                layoutRule.weightx = 1.0;
                layoutRule.weighty = 1.0;
                
                // button added to panel
                addToPanel(northPanel, layoutRule, basicButton, 0, 1, 1, 1);
                addToPanel(northPanel, layoutRule, engineeringButton, 1, 1, 1, 1);
                addToPanel(northPanel, layoutRule, programmerButton, 2, 1, 1, 1);
                addToPanel(northPanel, layoutRule, matrixButton, 3, 1, 1, 1);
                
                layoutRule.gridwidth = 4;
                layoutRule.gridheight = 1;
                layoutRule.fill = GridBagConstraints.HORIZONTAL;
                
                JLabel stringWindow = new JLabel("");
                
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
                centerProgrammerPanel.setLayout(new GridBagLayout());
                
                GridBagConstraints layoutRule = new GridBagConstraints();
                
                layoutRule.gridwidth = 1;
                layoutRule.gridheight = 1;
                layoutRule.weightx = 0.2;
                layoutRule.weighty = 0.2;
                
                layoutRule.fill = GridBagConstraints.CENTER;
                
                // �Ϲ� ���� ��ư �߰�
                for (int i =  0; i < opButtons.length; i++) {
                    opButtons[i] = new JButton(opSet[i]);
                    opButtons2[i] = new JButton(opSet[i]);
                    opButtons3[i] = new JButton(opSet[i]);
                    CalcListener c = new CalcListener();
                    opButtons[i].addActionListener(c);
                    opButtons2[i].addActionListener(c);
                    opButtons3[i].addActionListener(c);
                }
                
                // ���п� �����ư �߰�
                for(int i=0; i<opEngButtons.length; i++) {
                    opEngButtons[i] = new JButton(opEngSet[i]);
                    CalcListener c = new CalcListener();
                    opEngButtons[i].addActionListener(c);
                }
                
                // ���ڸ� �߰�
                for (int i = 0; i < 10; i++) {
                    numberButtons[i] = new JButton(String.valueOf(i));
                    numberButtons2[i] = new JButton(String.valueOf(i));
                    numberButtons3[i] = new JButton(String.valueOf(i));
                    CalcListener c = new CalcListener();
                    numberButtons[i].addActionListener(c);
                    numberButtons2[i].addActionListener(c);
                    numberButtons3[i].addActionListener(c);
                }
                
                // ���� ���� ��ư �߰�
                for(int i = 0 ; i < opLogicButtons.length ; i++) {
                    opLogicButtons[i] = new JButton(opLogicSet[i]);
                    CalcListener c = new CalcListener();
                    opLogicButtons[i].addActionListener(c);
                }
                
                for(int i = 0 ; i < opBinButtons.length ; i++) {
                    opBinButtons[i] = new JButton(opBinSet[i]);
                    CalcListener c = new CalcListener();
                    opBinButtons[i].addActionListener(c);
                    
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
                            centerProgrammerPanel.add(numberButtons3[numbering * 3 - 2 + j], layoutRule);
                        }
                        
                        for (int j = 3; j < buttonWidth; j++)
                        {
                            layoutRule.gridx = j;
                            centerBasicPanel.add(opButtons[j + (i * (buttonWidth - 3))], layoutRule);
                            centerEngineeringPanel.add(opButtons2[j + (i * (buttonWidth - 3))], layoutRule);
                            centerProgrammerPanel.add(opButtons3[j + (i * (buttonWidth - 3))], layoutRule);
                        }
                    }
                    
                    else
                    {
                        for (int j = 0; j < buttonWidth; j++)
                        {
                            layoutRule.gridx = j;
                            centerBasicPanel.add(opButtons[j + (i * buttonWidth)], layoutRule);
                            centerEngineeringPanel.add(opButtons2[j + (i * buttonWidth)], layoutRule);
                            centerProgrammerPanel.add(opButtons3[j + (i * buttonWidth)], layoutRule);
                        }
                    }
                }
                
                layoutRule.gridy = buttonHeight - 1;
                
                layoutRule.gridx = 0;
                centerBasicPanel.add(opButtons[opButtons.length - buttonWidth + 1], layoutRule);
                centerEngineeringPanel.add(opButtons2[opButtons2.length - buttonWidth + 1], layoutRule);
                centerProgrammerPanel.add(opButtons3[opButtons2.length - buttonWidth + 1], layoutRule);
                
                
                layoutRule.gridx = 1;
                centerBasicPanel.add(numberButtons[0], layoutRule);
                centerEngineeringPanel.add(numberButtons2[0], layoutRule);
                centerProgrammerPanel.add(numberButtons3[0], layoutRule);
                
                
                for (int i = opButtons.length - buttonWidth + 2; i < opButtons.length; i++)
                {
                    layoutRule.gridx = i - opButtons.length + buttonWidth;
                    centerBasicPanel.add(opButtons[i], layoutRule);
                    centerEngineeringPanel.add(opButtons2[i], layoutRule);
                    centerProgrammerPanel.add(opButtons3[i], layoutRule);
                }
                
                //eng ��ư �߰�
                for(int i=0;i<opEngButtons.length;i++) {
                    addToPanel(centerEngineeringPanel, layoutRule, opEngButtons[i], i, 6, 1, 1);
                }
                
                //������ư �߰�
                for(int i = 0 ; i < opLogicButtons.length ; i++) {
                    addToPanel(centerProgrammerPanel, layoutRule, opLogicButtons[i], i, 6, 1, 1);
                }
                
                // ���̳ʸ� ��ȯ ��ư �߰�
                for(int i = 0 ; i < opBinButtons.length ; i++) {
                    addToPanel(centerProgrammerPanel, layoutRule, opBinButtons[i], 5, i, 1, 1);
                }
                
                
                cardPanel.setLayout(card);
                cardPanel.add("basic", centerBasicPanel);
                cardPanel.add("engineering", centerEngineeringPanel);
                cardPanel.add("programmer", centerProgrammerPanel);
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
