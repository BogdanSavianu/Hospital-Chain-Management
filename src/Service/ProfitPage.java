package Service;

import Service.ProfitCalculator;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class ProfitPage extends JFrame {
    private JLabel totalSalariesLabel;
    private JLabel totalServicePricesLabel;
    private JLabel profitLabel;

    public ProfitPage() {
        initializeComponents();
        displayProfitValues();
    }

    private void initializeComponents() {
        setTitle("Profit Information");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        totalSalariesLabel = new JLabel("Total Salaries: ");
        totalServicePricesLabel = new JLabel("Total Service Prices: ");
        profitLabel = new JLabel("Profit: ");

        JPanel contentPane = new JPanel(new GridLayout(3, 1, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        contentPane.add(totalSalariesLabel);
        contentPane.add(totalServicePricesLabel);
        contentPane.add(profitLabel);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void displayProfitValues() {
        ProfitCalculator.ProfitResult result = ProfitCalculator.calculateProfit();

        if (result != null) {
            totalSalariesLabel.setText("Total Salaries: " + result.getTotalSalaries());
            totalServicePricesLabel.setText("Total Service Prices: " + result.getTotalServicePrices());
            profitLabel.setText("Profit: " + result.getProfit());
        } else {
            totalSalariesLabel.setText("Error retrieving data.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProfitPage());
    }
}
