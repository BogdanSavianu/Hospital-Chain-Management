package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static Repository.AngajatRepository.stergeAngajat;

public class StergereAngajatPage extends JFrame {
    private JPanel contentPane;
    private JTextField angajatIdTextField;
    private JButton deleteAngajatButton;
    private JButton backButton;
    private Integer id;

    public StergereAngajatPage(Integer id) {
        this.id = id;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Stergere Angajat");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new GridLayout(4, 1, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        angajatIdTextField = new JTextField();
        deleteAngajatButton = new JButton("Delete Angajat");
        backButton = new JButton("Back");

        deleteAngajatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAngajat();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        contentPane.add(new JLabel("Enter Angajat ID:"));
        contentPane.add(angajatIdTextField);
        contentPane.add(deleteAngajatButton);
        contentPane.add(backButton);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void deleteAngajat() {
        try {
            int angajatId = Integer.parseInt(angajatIdTextField.getText());
            stergeAngajat(angajatId);
            JOptionPane.showMessageDialog(this, "Angajat deleted successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid numeric ID.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        dispose();
        new HomePageResurseUmane(id).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StergereAngajatPage(1));
    }
}
