package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Model.Angajat;
import Model.Autentificare;
import Repository.UtilizatoriRepository;
import Service.HomePage;

public class Login extends JFrame {
    private JTextField helloTextField;
    private JPasswordField passwordField1;
    private JButton logInButton;

    public Login() {
        helloTextField = new JTextField();
        passwordField1 = new JPasswordField();
        logInButton = new JButton("Log in");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        JLabel userLabel = new JLabel("User ID:");
        userLabel.setFont(labelFont);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);

        Dimension textFieldSize = new Dimension(200, 30);
        helloTextField.setPreferredSize(textFieldSize);
        passwordField1.setPreferredSize(textFieldSize);

        logInButton.setFont(labelFont);
        logInButton.setBackground(new Color(59, 89, 182));
        logInButton.setForeground(Color.WHITE);
        logInButton.setFocusPainted(false);

        panel.add(userLabel);
        panel.add(helloTextField);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        panel.add(passwordLabel);
        panel.add(passwordField1);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        panel.add(logInButton);

        panel.setBackground(new Color(240, 240, 240));

        add(panel, BorderLayout.CENTER);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer userId = Integer.parseInt(helloTextField.getText());
                    String password = new String(passwordField1.getPassword());

                    Autentificare resultSet = UtilizatoriRepository.getUser(userId, password);

                    if (resultSet != null) {
                        if (userId.equals(resultSet.getIdUtilizator()) && password.equals(resultSet.getParola())) {
                            JOptionPane.showMessageDialog(Login.this, "Login successful!");
                            dispose();
                            Angajat angajat = UtilizatoriRepository.searchAngajat(userId);
                            if(angajat != null && angajat.getTipFunctie().equals("Receptioner"))
                                new HomePage(angajat.getIdUtilizator()).setVisible(true);
                            else if(angajat!=null && angajat.getTipFunctie().equals("Inspector Resurse Umane"))
                                new HomePageResurseUmane(angajat.getIdUtilizator()).setVisible(true);
                            else if(angajat!=null && angajat.getTipFunctie().equalsIgnoreCase("Expert Financiar Contabil"))
                                new HomePageFinanciar(angajat.getIdUtilizator()).setVisible(true);
                            else if(angajat!=null && (angajat.getTipFunctie().equals("Medic")||angajat.getTipFunctie().equals("Asistent Medical")))
                                new HomePageMedicAndAsistent(angajat.getIdUtilizator());
                        }
                    } else {
                        JOptionPane.showMessageDialog(Login.this, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Login.this, "Invalid User ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(Login.this, "Error during login", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
                login.setSize(300, 200);
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            }
        });
    }
}
