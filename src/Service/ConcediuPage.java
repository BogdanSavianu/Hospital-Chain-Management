package Service;

import Configuration.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConcediuPage extends JFrame {
    private JPanel contentPane;
    private JTextField angajatIdTextField;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private JButton submitButton;
    private JButton backButton;
    private Integer idResurse;

    public ConcediuPage(Integer idResurse) {
        this.idResurse = idResurse;
        setTitle("Concediu Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel angajatIdLabel = new JLabel("Angajat ID:");
        angajatIdTextField = new JTextField();

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateTextField = new JTextField();

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateTextField = new JTextField();

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitConcediu();
            }
        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        contentPane.add(angajatIdLabel);
        contentPane.add(angajatIdTextField);
        contentPane.add(startDateLabel);
        contentPane.add(startDateTextField);
        contentPane.add(endDateLabel);
        contentPane.add(endDateTextField);
        contentPane.add(new JLabel());
        contentPane.add(submitButton);
        contentPane.add(new JLabel());
        contentPane.add(backButton);

        add(contentPane);
        setVisible(true);
    }

    private void submitConcediu() {
        try {
            int angajatId = Integer.parseInt(angajatIdTextField.getText());
            String startDate = startDateTextField.getText();
            String endDate = endDateTextField.getText();

            Connection connection = DatabaseConnection.getConnection();

            String query = "INSERT INTO concedii (ID_Angajat, Data_Inceput, Data_Sfarsit) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, angajatId);
                preparedStatement.setString(2, startDate);
                preparedStatement.setString(3, endDate);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Concediu added successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add concediu. Please check your input.");
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void goBack() {
        dispose();
        new HomePageResurseUmane(idResurse).setVisible(true);
    }

    private void clearFields() {
        angajatIdTextField.setText("");
        startDateTextField.setText("");
        endDateTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConcediuPage(11));
    }
}
