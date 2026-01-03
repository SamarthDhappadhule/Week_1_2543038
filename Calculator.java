import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {

    TextField display;
    Button[] numbers = new Button[10];
    Button add, sub, mul, div, equal, clear;

    double num1, num2;
    char operator;

   Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setLayout(null);
         setVisible(true);
      
        display = new TextField();
        display.setBounds(30, 50, 220, 40);
        display.setEditable(false);
        add(display);

        
        int x = 30, y = 110, n = 1;
        for (int i = 1; i <= 9; i++) {
            numbers[i] = new Button(String.valueOf(i));
            numbers[i].setBounds(x, y, 50, 50);
            numbers[i].addActionListener(this);
            add(numbers[i]);

            x += 60;
            if (i % 3 == 0) {
                x = 30;
                y += 60;
            }
        }

        numbers[0] = new Button("0");
        numbers[0].setBounds(30, 290, 110, 50);
        numbers[0].addActionListener(this);
        add(numbers[0]);

        
        add = new Button("+");
        sub = new Button("-");
        mul = new Button("*");
        div = new Button("/");
        equal = new Button("=");
        clear = new Button("C");

        add.setBounds(200, 110, 50, 50);
        sub.setBounds(200, 170, 50, 50);
        mul.setBounds(200, 230, 50, 50);
        div.setBounds(200, 290, 50, 50);
        equal.setBounds(30, 350, 110, 40);
        clear.setBounds(150, 350, 100, 40);

        Button[] ops = { add, sub, mul, div, equal, clear };
        for (Button b : ops) {
            b.addActionListener(this);
            add(b);
        }

    }

    public void actionPerformed(ActionEvent e) {

        
        for (int i = 0; i <= 9; i++) {
            if (e.getSource() == numbers[i]) {
                display.setText(display.getText() + i);
            }
        }

        if (e.getSource() == add || e.getSource() == sub ||
            e.getSource() == mul || e.getSource() == div) {

            num1 = Double.parseDouble(display.getText());
            operator = ((Button) e.getSource()).getLabel().charAt(0);
            display.setText("");
        }

        
        if (e.getSource() == equal) {
            try {
                num2 = Double.parseDouble(display.getText());

                if (operator == '+') display.setText("" + (num1 + num2));
                else if (operator == '-') display.setText("" + (num1 - num2));
                else if (operator == '*') display.setText("" + (num1 * num2));
                else if (operator == '/') {
                    if (num2 == 0)
                        throw new ArithmeticException();
                    display.setText("" + (num1 / num2));
                }

            } catch (ArithmeticException ex) {
                display.setText("Divide by 0 Error");
            }
        }

      
        if (e.getSource() == clear) {
            display.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

