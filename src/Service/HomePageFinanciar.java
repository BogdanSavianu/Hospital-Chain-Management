package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageFinanciar extends JFrame {
    private JPanel contentPane;
    private JButton viewProfitButton;
    private JButton viewMedicProfitButton;
    private JButton veziSalariiButton;
    private JButton viewPersonalInfoButton;
    private JButton signOutButton;

    private Integer id;

    public HomePageFinanciar(Integer id) {
        this.id = id;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Financiar Home Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new GridLayout(6, 1, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        viewProfitButton = new JButton("View Profit Information");
        viewProfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProfitPage();
            }
        });

        viewMedicProfitButton = new JButton("View Medic Profit");
        viewMedicProfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMedicProfitPage();
            }
        });

        veziSalariiButton = new JButton("Vezi Salarii");
        veziSalariiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSalariiViewPage();
            }
        });

        viewPersonalInfoButton = new JButton("View Personal Info");
        viewPersonalInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewOwnInfoPage();
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
        contentPane.add(viewMedicProfitButton);
        contentPane.add(veziSalariiButton);
        contentPane.add(viewPersonalInfoButton);
        contentPane.add(signOutButton);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void openProfitPage() {
        ProfitPage profitPage = new ProfitPage();
        profitPage.setVisible(true);
    }

    private void openMedicProfitPage() {
        MedicProfitPage medicProfitPage = new MedicProfitPage();
        medicProfitPage.setVisible(true);
    }

    private void openSalariiViewPage() {
        String angajatIdInput = JOptionPane.showInputDialog(
                this,
                "Enter the ID of the angajat:",
                "Enter Angajat ID",
                JOptionPane.QUESTION_MESSAGE
        );

        if (angajatIdInput != null && !angajatIdInput.isEmpty()) {
            try {
                int angajatId = Integer.parseInt(angajatIdInput);
                SalariiView salariiViewPage = new SalariiView(angajatId);
                salariiViewPage.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid input. Please enter a valid numeric ID.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void openViewOwnInfoPage() {
        ViewOwnInfo viewOwnInfo = new ViewOwnInfo(id);
        viewOwnInfo.setVisible(true);
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
        SwingUtilities.invokeLater(() -> new HomePageFinanciar(25));
    }
}
