package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private JPanel contentPane;
    private JButton adaugaRaportButton;
    private JButton adaugaProgramareButton;
    private JButton viewAppointmentsButton;  // New button for viewing all appointments
    private JButton signOutButton;
    private JLabel label;

    private int idReceptioner;  // New field to store the idReceptioner
    private String role;  // New field to store the role of the employee

    // Modified constructor to receive the idReceptioner and role
    public HomePage(int idReceptioner, String role) {
        this.idReceptioner = idReceptioner;
        this.role = role;
        initializeComponents();
    }

    public HomePage(int idReceptioner){
        this.idReceptioner=idReceptioner;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Home Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        label = new JLabel();

        adaugaRaportButton = new JButton("Adauga Raport Medical");
        adaugaRaportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openConsultationPage();
            }
        });

        adaugaProgramareButton = new JButton("Adauga Programare");
        adaugaProgramareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProgramarePage();
            }
        });

        // Create the viewAppointmentsButton and set its visibility based on the role
        viewAppointmentsButton = new JButton("Vezi programarile");
        viewAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewAppointmentsPage();
            }
        });
        viewAppointmentsButton.setVisible("Administrator".equals(role) || "SuperAdministrator".equals(role));

        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignOut();
            }
        });

        contentPane = new JPanel(new GridLayout(4, 1, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        contentPane.add(adaugaRaportButton);
        contentPane.add(adaugaProgramareButton);
        contentPane.add(viewAppointmentsButton);
        contentPane.add(signOutButton);
        contentPane.add(label);

        add(contentPane);

        setVisible(true);
    }

    private void openConsultationPage() {
        DateConsultatie consultatiePage = new DateConsultatie(this, idReceptioner);
        consultatiePage.setVisible(true);
        this.setVisible(false);
    }

    private void openProgramarePage() {
        ProgramarePage programare = new ProgramarePage(this, idReceptioner);
        programare.setVisible(true);
        this.setVisible(false);
    }

    private void openViewAppointmentsPage() {
        ViewProgramariPage viewAppointmentsPage = new ViewProgramariPage(idReceptioner);
        viewAppointmentsPage.setVisible(true);
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
                int receptionistId = 10;
                String receptionistRole = "Administrator";
                new HomePage(receptionistId, receptionistRole);
            }
        });
    }
}
