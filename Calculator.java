
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField displayField;
    private double num1, num2, result;
    private char operator;

    public Calculator() {
        // --- Frame Setup ---
        setTitle("Basic Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- Display Field (North) ---
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.BOLD, 32));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        add(displayField, BorderLayout.NORTH);

        // --- Button Panel (Center) ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); // 4x4 grid with gaps
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Button labels in order
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        char cmdChar = command.charAt(0);

        // Check if the command is a number
        if (Character.isDigit(cmdChar)) {
            displayField.setText(displayField.getText() + command);
        } // Clear button
        else if (cmdChar == 'C') {
            displayField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
        } // Equals button
        else if (cmdChar == '=') {
            num2 = Double.parseDouble(displayField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        displayField.setText("Error");
                        return;
                    }
                    break;
            }
            // Display result, remove .0 if it's a whole number
            displayField.setText(String.valueOf(result).endsWith(".0") ? String.valueOf((int) result) : String.valueOf(result));
            num1 = result; // For continuous calculation
        } // Operator button
        else {
            num1 = Double.parseDouble(displayField.getText());
            operator = cmdChar;
            displayField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator().setVisible(true));
    }
}
