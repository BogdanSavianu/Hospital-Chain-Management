package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageMedicAndAsistent extends JFrame {
    private JPanel contentPane;
    private JButton pacientViewButton;
    private JButton viewAngajatButton;
    private JButton viewProgramariButton;
    private JButton viewConsultatiiButton;
    private JButton signOutButton;

    private Integer id;

    public HomePageMedicAndAsistent(int id) {
        this.id = id;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Home Page Medic/Asistent");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(5, 1));

        pacientViewButton = new JButton("Pacient View");
        pacientViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPacientView();
            }
        });

        viewAngajatButton = new JButton("View Personal Info");
        viewAngajatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewAngajatPage();
            }
        });

        viewProgramariButton = new JButton("View Programari Page");
        viewProgramariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewProgramariPage();
            }
        });

        viewConsultatiiButton = new JButton("View Consultatii Page");
        viewConsultatiiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewConsultatiiPage();
            }
        });

        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signOut();
            }
        });

        contentPane.add(pacientViewButton);
        contentPane.add(viewAngajatButton);
        contentPane.add(viewProgramariButton);
        contentPane.add(viewConsultatiiButton);
        contentPane.add(signOutButton);

        add(contentPane);

        setVisible(true);
    }

    private void openPacientView() {
        new PacientView().setVisible(true);
    }

    private void openViewAngajatPage() {
        new ViewOwnInfo(id).setVisible(true);
    }

    private void openViewProgramariPage() {
        new ViewProgramariPage(id).setVisible(true);
    }

    private void openViewConsultatiiPage() {
        new ViewConsultatii().setVisible(true);
    }

    private void signOut() {
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to sign out?",
                "Sign Out Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            this.dispose();
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
                int medicAsistentId = 24;
                new HomePageMedicAndAsistent(medicAsistentId);
            }
        });
    }
}
