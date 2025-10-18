
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingCart extends JFrame {

    private JCheckBox laptopCheckBox;
    private JCheckBox phoneCheckBox;
    private JCheckBox headphonesCheckBox;
    private JButton generateBillButton;
    private JTextArea billTextArea;

    // Prices for the items
    private static final double LAPTOP_PRICE = 1200.50;
    private static final double PHONE_PRICE = 800.75;
    private static final double HEADPHONES_PRICE = 150.00;

    public ShoppingCart() {
        // --- Frame Setup ---
        setTitle("Shopping Cart");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps

        // --- North Panel for Title ---
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("Select Your Items");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // --- Center Panel for Checkboxes ---
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        laptopCheckBox = new JCheckBox("Laptop - $" + LAPTOP_PRICE);
        phoneCheckBox = new JCheckBox("Phone - $" + PHONE_PRICE);
        headphonesCheckBox = new JCheckBox("Headphones - $" + HEADPHONES_PRICE);

        // Style checkboxes
        Font itemFont = new Font("Arial", Font.PLAIN, 14);
        laptopCheckBox.setFont(itemFont);
        phoneCheckBox.setFont(itemFont);
        headphonesCheckBox.setFont(itemFont);

        itemsPanel.add(laptopCheckBox);
        itemsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        itemsPanel.add(phoneCheckBox);
        itemsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        itemsPanel.add(headphonesCheckBox);

        add(itemsPanel, BorderLayout.CENTER);

        // --- South Panel for Button and Bill ---
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        generateBillButton = new JButton("Generate Bill");
        billTextArea = new JTextArea(10, 30);
        billTextArea.setEditable(false);
        billTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(billTextArea); // Add a scroll pane

        bottomPanel.add(generateBillButton, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        // --- Event Listener for the Button ---
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });
    }

    private void generateBill() {
        StringBuilder bill = new StringBuilder("--- Your Bill ---\n\n");
        double total = 0.0;

        if (laptopCheckBox.isSelected()) {
            bill.append(String.format("%-20s $%10.2f\n", "Laptop", LAPTOP_PRICE));
            total += LAPTOP_PRICE;
        }
        if (phoneCheckBox.isSelected()) {
            bill.append(String.format("%-20s $%10.2f\n", "Phone", PHONE_PRICE));
            total += PHONE_PRICE;
        }
        if (headphonesCheckBox.isSelected()) {
            bill.append(String.format("%-20s $%10.2f\n", "Headphones", HEADPHONES_PRICE));
            total += HEADPHONES_PRICE;
        }

        bill.append("\n--------------------------------\n");
        bill.append(String.format("%-20s $%10.2f\n", "TOTAL", total));

        billTextArea.setText(bill.toString());
    }

    public static void main(String[] args) {
        // Run the GUI code on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShoppingCart().setVisible(true);
            }
        });
    }
}
