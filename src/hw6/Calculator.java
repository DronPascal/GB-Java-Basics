package hw6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    private static JTextField inputBox;

    public static void main(String[] args) {
        createWindow();
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI(frame);
        frame.setSize(240, 260);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(JFrame frame) {
        JPanel panel = new JPanel();
        Calculator calculator = new Calculator();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());

        inputBox = new JTextField(10);
        inputBox.setEditable(false);
        inputBox.setFont(inputBox.getFont().deriveFont(24f));
        String[][] buttonsNames = {{"1", "2", "3", "+"},
                {"4", "5", "6", "-"},
                {"7", "8", "9", "/"},
                {".", "0", "*", "="}};
        JButton[][] buttons = new JButton[4][4];

        for (int i = 0; i < buttonsNames.length; i++)
            for (int j = 0; j < buttonsNames[0].length; j++) {
                buttons[i][j] = new JButton(buttonsNames[i][j]);
                buttons[i][j].addActionListener(calculator);
                buttons[i][j].setFont(buttons[i][j].getFont().deriveFont(20f));
            }
        JButton delBut = new JButton("C");
        delBut.setFont(delBut.getFont().deriveFont(20f));
        delBut.addActionListener(calculator);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(inputBox, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(delBut, gbc);
        for (int i = 0; i < buttonsNames.length; i++)
            for (int j = 0; j < buttonsNames[0].length; j++) {
                gbc.gridx = j;
                gbc.gridy = i + 1;
                panel.add(buttons[i][j], gbc);
            }

        frame.getContentPane().add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.charAt(0) == 'C')
            inputBox.setText("");
        else if (command.charAt(0) == '=')
            inputBox.setText(evaluate(inputBox.getText()));
        else
            inputBox.setText(inputBox.getText() + command);

    }

    public static String evaluate(String expression) {
        char[] arr = expression.toCharArray();
        String operand1 = "";
        String operand2 = "";
        String operator = "";
        double result = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= '0' && arr[i] <= '9' || arr[i] == '.' || (arr[i] == '-' && i == 0)) {
                if (operator.isEmpty())
                    operand1 += arr[i];
                else
                    operand2 += arr[i];
            }

            if ((arr[i] == '+' || arr[i] == '-' || arr[i] == '/' || arr[i] == '*') && i != 0)
                operator += arr[i];
        }

        if (operator.equals("+"))
            result = (Double.parseDouble(operand1) + Double.parseDouble(operand2));
        else if (operator.equals("-"))
            result = (Double.parseDouble(operand1) - Double.parseDouble(operand2));
        else if (operator.equals("/"))
            result = (Double.parseDouble(operand1) / Double.parseDouble(operand2));
        else
            result = (Double.parseDouble(operand1) * Double.parseDouble(operand2));
        return result - (int) result != 0 ? (Math.round(result * 100000) / 100000.0d) + "" : (int) result + "";
    }
}