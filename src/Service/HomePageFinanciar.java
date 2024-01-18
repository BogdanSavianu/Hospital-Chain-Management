package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageFinanciar extends JFrame {
    private JPanel contentPane;
    private JButton viewProfitButton;
    private JButton viewMedicProfitButton; // New button for viewing Medic Profit
    private JButton signOutButton;

    public HomePageFinanciar() {
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Financiar Home Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new GridLayout(4, 1, 10, 10)); // Increase rows to accommodate the new button
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        viewProfitButton = new JButton("View Profit Information");
        viewProfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProfitPage();
            }
        });

        viewMedicProfitButton = new JButton("View Medic Profit"); // New button
        viewMedicProfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMedicProfitPage();
            }
        });

        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignOut();
            }
        });

        contentPane.add(viewProfitButton);
        contentPane.add(viewMedicProfitButton); // Add the new button
        contentPane.add(signOutButton);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void openProfitPage() {
        ProfitPage profitPage = new ProfitPage();
        profitPage.setVisible(true);
    }

    private void openMedicProfitPage() {
        // Open the MedicProfitPage
        MedicProfitPage medicProfitPage = new MedicProfitPage();
        medicProfitPage.setVisible(true);
    }

    private void handleSignOut() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to log out?",
                "Confirm Sign Out",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            dispose();
            Login login = new Login();
            login.setSize(300, 200);
            login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePageFinanciar());
    }
}
