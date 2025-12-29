import java.awt.*;
import java.awt.event.*;

public class AWTCalculator extends Frame implements ActionListener {

    TextField display;
    Button[] numButtons = new Button[10];
    Button add, sub, mul, div, equal, clear, dot;

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    AWTCalculator() {
        setTitle("Calculator");
        setSize(320, 420);
        setLayout(null);

        // Display
        display = new TextField();
        display.setBounds(20, 50, 260, 40);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        add(display);

        // Number Buttons
        int x = 20, y = 110;
        for (int i = 0; i <= 9; i++) {
            numButtons[i] = new Button(String.valueOf(i));
            numButtons[i].setFont(new Font("Arial", Font.BOLD, 14));
            numButtons[i].addActionListener(this);
        }

        // Place numbers
        int n = 1;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                numButtons[n].setBounds(x + col * 60, y + row * 60, 50, 50);
                add(numButtons[n]);
                n++;
            }
        }

        numButtons[0].setBounds(20, 290, 110, 50);
        add(numButtons[0]);

        // Operators
        add = new Button("+");
        sub = new Button("-");
        mul = new Button("*");
        div = new Button("/");
        equal = new Button("=");
        clear = new Button("C");
        dot = new Button(".");

        Button[] ops = { add, sub, mul, div, equal, clear, dot };
        int oy = 110;

        for (Button b : ops) {
            b.setFont(new Font("Arial", Font.BOLD, 14));
            b.addActionListener(this);
        }

        add.setBounds(200, oy, 50, 50);
        sub.setBounds(200, oy + 60, 50, 50);
        mul.setBounds(200, oy + 120, 50, 50);
        div.setBounds(200, oy + 180, 50, 50);
        equal.setBounds(140, 290, 50, 50);
        dot.setBounds(200, 290, 50, 50);
        clear.setBounds(20, 350, 230, 40);

        add(add); add(sub); add(mul); add(div);
        add(equal); add(dot); add(clear);

        // Close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Numbers
        for (int i = 0; i <= 9; i++) {
            if (e.getSource() == numButtons[i]) {
                display.setText(display.getText() + i);
            }
        }

        // Decimal
        if (e.getSource() == dot) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }

        // Operators
        if (e.getSource() == add || e.getSource() == sub ||
            e.getSource() == mul || e.getSource() == div) {

            num1 = Double.parseDouble(display.getText());
            operator = ((Button) e.getSource()).getLabel().charAt(0);
            display.setText("");
        }

        // Equals
        if (e.getSource() == equal) {
            try {
                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0)
                            throw new ArithmeticException();
                        result = num1 / num2;
                        break;
                }

                display.setText(String.valueOf(result));

            } catch (ArithmeticException ex) {
                display.setText("Error: Divide by 0");
            } catch (Exception ex) {
                display.setText("Invalid Input");
            }
        }

        // Clear
        if (e.getSource() == clear) {
            display.setText("");
            num1 = num2 = result = 0;
        }
    }

    public static void main(String[] args) {
        new AWTCalculator();
    }
}
