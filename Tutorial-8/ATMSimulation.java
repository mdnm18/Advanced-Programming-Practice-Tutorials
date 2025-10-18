
import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

public class ATMSimulation extends JFrame {

    private static final String CORRECT_PIN = "1234";
    private double balance = 5000.00;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPasswordField pinField;

    public ATMSimulation() {
        // --- Frame Setup ---
        setTitle("ATM Simulation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Main Panel with CardLayout ---
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // --- Panel 1: PIN Entry ---
        JPanel pinPanel = createPinPanel();
        mainPanel.add(pinPanel, "PIN_PANEL");

        // --- Panel 2: Options ---
        JPanel optionsPanel = createOptionsPanel();
        mainPanel.add(optionsPanel, "OPTIONS_PANEL");

        add(mainPanel);
    }

    private JPanel createPinPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel pinLabel = new JLabel("Enter Your PIN:");
        pinLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(pinLabel, gbc);

        pinField = new JPasswordField(10);
        pinField.setHorizontalAlignment(JPasswordField.CENTER);
        pinField.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(pinField, gbc);

        JButton enterButton = new JButton("Enter");
        panel.add(enterButton, gbc);

        // Action listener for the enter button
        enterButton.addActionListener(e -> {
            char[] inputPin = pinField.getPassword();
            if (CORRECT_PIN.equals(new String(inputPin))) {
                // Correct PIN, switch to options panel
                cardLayout.show(mainPanel, "OPTIONS_PANEL");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect PIN. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Clear the PIN field for security
            pinField.setText("");
            Arrays.fill(inputPin, '0');
        });

        return panel;
    }

    private JPanel createOptionsPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel welcomeLabel = new JLabel("Select an Option", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        panel.add(welcomeLabel);
        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);

        // Action Listeners for buttons
        checkBalanceButton.addActionListener(e
                -> JOptionPane.showMessageDialog(this, String.format("Your current balance is: $%.2f", balance), "Balance", JOptionPane.INFORMATION_MESSAGE)
        );

        depositButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:", "Deposit", JOptionPane.PLAIN_MESSAGE);
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0) {
                    balance += amount;
                    JOptionPane.showMessageDialog(this, String.format("$%.2f deposited successfully.\nNew balance: $%.2f", amount, balance));
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                if (amountStr != null) { // User didn't cancel
                    JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        withdrawButton.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:", "Withdraw", JOptionPane.PLAIN_MESSAGE);
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    JOptionPane.showMessageDialog(this, String.format("$%.2f withdrawn successfully.\nNew balance: $%.2f", amount, balance));
                } else if (amount > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                if (amountStr != null) { // User didn't cancel
                    JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMSimulation().setVisible(true));
    }
}
