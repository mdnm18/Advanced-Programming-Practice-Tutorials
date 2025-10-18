
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeedbackForm extends JFrame {

    private JRadioButton star1, star2, star3, star4, star5;
    private ButtonGroup ratingGroup;
    private JTextArea commentsArea;
    private JButton submitButton;

    public FeedbackForm() {
        // --- Frame Setup ---
        setTitle("Feedback Form");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Main Panel with Padding ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // --- Rating Panel ---
        JPanel ratingPanel = new JPanel();
        ratingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ratingPanel.setBorder(BorderFactory.createTitledBorder("Rate Our Service"));

        ratingGroup = new ButtonGroup();
        star1 = new JRadioButton("1 Star");
        star2 = new JRadioButton("2 Stars");
        star3 = new JRadioButton("3 Stars");
        star4 = new JRadioButton("4 Stars");
        star5 = new JRadioButton("5 Stars");

        ratingGroup.add(star1);
        ratingGroup.add(star2);
        ratingGroup.add(star3);
        ratingGroup.add(star4);
        ratingGroup.add(star5);

        ratingPanel.add(star1);
        ratingPanel.add(star2);
        ratingPanel.add(star3);
        ratingPanel.add(star4);
        ratingPanel.add(star5);

        // --- Comments Panel ---
        JPanel commentsPanel = new JPanel(new BorderLayout());
        commentsPanel.setBorder(BorderFactory.createTitledBorder("Comments"));

        commentsArea = new JTextArea(5, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(commentsArea);
        commentsPanel.add(scrollPane, BorderLayout.CENTER);

        // --- Submit Button ---
        submitButton = new JButton("Submit Feedback");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);

        // Add components to main panel
        mainPanel.add(ratingPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(commentsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(buttonPanel);

        // --- Submit Button Action Listener ---
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a rating was selected
                if (ratingGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(FeedbackForm.this,
                            "Please select a star rating.",
                            "Rating Required",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Show thank you message
                JOptionPane.showMessageDialog(FeedbackForm.this,
                        "Thank you for your feedback!",
                        "Submission Successful",
                        JOptionPane.INFORMATION_MESSAGE);

                // Reset the form
                ratingGroup.clearSelection();
                commentsArea.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeedbackForm().setVisible(true));
    }
}
