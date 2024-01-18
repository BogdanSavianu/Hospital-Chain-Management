package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageResurseUmane extends JFrame {
    private JPanel contentPane;
    private JButton searchAngajatButton;
    private JButton adaugaAngajatButton;
    private JButton signOutButton;  // Added Sign Out button

    public HomePageResurseUmane() {
        setTitle("Main Application Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new GridLayout(3, 1, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        searchAngajatButton = new JButton("Search Angajat");
        adaugaAngajatButton = new JButton("Adauga Angajat");
        signOutButton = new JButton("Sign Out");  // Added Sign Out button

        searchAngajatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchAngajatPage();
            }
        });

        adaugaAngajatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAdaugaAngajatPage();
            }
        });

        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignOut();
            }
        });

        contentPane.add(searchAngajatButton);
        contentPane.add(adaugaAngajatButton);
        contentPane.add(signOutButton);  // Added Sign Out button

        add(contentPane);
        setVisible(true);
    }

    private void openSearchAngajatPage() {
        ViewAngajatPage viewAngajatPage = new ViewAngajatPage();
        viewAngajatPage.setVisible(true);
    }

    private void openAdaugaAngajatPage() {
        AdaugaAngajat adaugaAngajat = new AdaugaAngajat(this);
        adaugaAngajat.setVisible(true);
        this.setVisible(false);
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePageResurseUmane();
            }
        });
    }
}
