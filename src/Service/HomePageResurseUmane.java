package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageResurseUmane extends JFrame {
    private JPanel contentPane;
    private JButton searchAngajatButton;
    private JButton adaugaAngajatButton;
    private JButton stergereAngajatButton;
    private JButton viewPersonalInfoButton;
    private JButton adaugaConcediuButton;
    private JButton signOutButton;
    private Integer id;

    public HomePageResurseUmane(Integer id) {
        this.id = id;
        setTitle("Main Application Page");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new GridLayout(6, 1, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        searchAngajatButton = new JButton("Search Angajat");
        adaugaAngajatButton = new JButton("Adauga Angajat");
        stergereAngajatButton = new JButton("Stergere Angajat");
        viewPersonalInfoButton = new JButton("View Personal Info");
        adaugaConcediuButton = new JButton("Adauga Concediu"); // New button
        signOutButton = new JButton("Sign Out");

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

        stergereAngajatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStergereAngajatPage();
            }
        });

        viewPersonalInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewOwnInfoPage();
            }
        });

        adaugaConcediuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAdaugaConcediuPage();
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
        contentPane.add(stergereAngajatButton);
        contentPane.add(viewPersonalInfoButton);
        contentPane.add(adaugaConcediuButton);
        contentPane.add(signOutButton);

        add(contentPane);
        setVisible(true);
    }

    private void openSearchAngajatPage() {
        ViewAngajatPage viewAngajatPage = new ViewAngajatPage(id);
        viewAngajatPage.setVisible(true);
    }

    private void openAdaugaAngajatPage() {
        AdaugaAngajat adaugaAngajat = new AdaugaAngajat(this, id);
        adaugaAngajat.setVisible(true);
        this.setVisible(false);
    }

    private void openStergereAngajatPage() {
        dispose();
        StergereAngajatPage stergereAngajatPage = new StergereAngajatPage(id);
        stergereAngajatPage.setVisible(true);
    }

    private void openViewOwnInfoPage() {
        ViewOwnInfo viewOwnInfo = new ViewOwnInfo(id);
        viewOwnInfo.setVisible(true);
    }

    private void openAdaugaConcediuPage() {
        ConcediuPage concediuPage = new ConcediuPage(id);
        concediuPage.setVisible(true);
    }

    private void handleSignOut() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to log out?",
                "Confirm Sign Out",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            this.dispose();
            Login login = new Login();
            login.setSize(300, 200);
            login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePageResurseUmane(1));
    }
}
