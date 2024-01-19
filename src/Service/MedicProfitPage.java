package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class MedicProfitPage extends JFrame {
    private JPanel contentPane;
    private JTextField medicIdTextField;
    private JTextField monthTextField;
    private JTextField yearTextField;
    private JButton calculateProfitButton;
    private JButton backButton;

    public MedicProfitPage() {
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Medic Profit Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new GridLayout(5, 2, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        medicIdTextField = new JTextField();
        monthTextField = new JTextField();
        yearTextField = new JTextField();

        calculateProfitButton = new JButton("Calculate Profit");
        calculateProfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndShowProfit();
            }
        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        contentPane.add(new JLabel("Medic ID:"));
        contentPane.add(medicIdTextField);
        contentPane.add(new JLabel("Month:"));
        contentPane.add(monthTextField);
        contentPane.add(new JLabel("Year:"));
        contentPane.add(yearTextField);
        contentPane.add(new JLabel());
        contentPane.add(calculateProfitButton);
        contentPane.add(new JLabel());
        contentPane.add(backButton);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void calculateAndShowProfit() {
        try {
            int medicId = Integer.parseInt(medicIdTextField.getText());
            int month = Integer.parseInt(monthTextField.getText());
            int year = Integer.parseInt(yearTextField.getText());

            Double profit = ProfitCalculator.calculateMedicProfit(medicId, month, year);

            JOptionPane.showMessageDialog(this, "Medic Profit: " + profit, "Profit Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MedicProfitPage());
    }
}
