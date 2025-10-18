
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagement extends JFrame {

    private JTextField nameField, deptField, salaryField;
    private JButton addButton;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public EmployeeManagement() {
        // --- Frame Setup ---
        setTitle("Employee Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Input Panel (Top) ---
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add New Employee"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Labels
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Department:"), gbc);
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Salary:"), gbc);

        // Text Fields
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        nameField = new JTextField(20);
        inputPanel.add(nameField, gbc);
        gbc.gridy = 1;
        deptField = new JTextField(20);
        inputPanel.add(deptField, gbc);
        gbc.gridy = 2;
        salaryField = new JTextField(20);
        inputPanel.add(salaryField, gbc);

        // Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        addButton = new JButton("Add Employee");
        inputPanel.add(addButton, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // --- Table (Center) ---
        String[] columnNames = {"Name", "Department", "Salary"};
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Employee List"));
        add(scrollPane, BorderLayout.CENTER);

        // --- Add Button Action Listener ---
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
    }

    private void addEmployee() {
        String name = nameField.getText();
        String department = deptField.getText();
        String salaryStr = salaryField.getText();

        if (name.isEmpty() || department.isEmpty() || salaryStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Validate that salary is a number
            Double.parseDouble(salaryStr);

            // Add data to the table model
            tableModel.addRow(new Object[]{name, department, salaryStr});

            // Clear input fields after adding
            nameField.setText("");
            deptField.setText("");
            salaryField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for salary.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagement().setVisible(true));
    }
}
