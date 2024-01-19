package Service;

import Configuration.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewOwnInfo extends JFrame {
    private JPanel contentPane;
    private JLabel lblCnp;
    private JLabel lblNume;
    private JLabel lblPrenume;
    private JLabel lblAdresa;
    private JLabel lblTelefon;
    private JLabel lblEmail;
    private JLabel lblIban;
    private JLabel lblNumarContract;
    private JLabel lblDataAngajarii;
    private JLabel lblFunctie;
    private JLabel lblTipAdministrator;

    private Integer idUtilizator;

    public ViewOwnInfo(int idUtilizator) {
        this.idUtilizator = idUtilizator;
        initializeComponents();
        loadUserInfo();
    }

    private void initializeComponents() {
        setTitle("View Own Information");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(11, 2));

        lblCnp = new JLabel("CNP:");
        lblNume = new JLabel("Nume:");
        lblPrenume = new JLabel("Prenume:");
        lblAdresa = new JLabel("Adresa:");
        lblTelefon = new JLabel("Telefon:");
        lblEmail = new JLabel("Email:");
        lblIban = new JLabel("IBAN:");
        lblNumarContract = new JLabel("Numar Contract:");
        lblDataAngajarii = new JLabel("Data Angajarii:");
        lblFunctie = new JLabel("Functie:");
        lblTipAdministrator = new JLabel("Tip Administrator:");

        contentPane.add(lblCnp);
        contentPane.add(new JLabel());
        contentPane.add(lblNume);
        contentPane.add(new JLabel());
        contentPane.add(lblPrenume);
        contentPane.add(new JLabel());
        contentPane.add(lblAdresa);
        contentPane.add(new JLabel());
        contentPane.add(lblTelefon);
        contentPane.add(new JLabel());
        contentPane.add(lblEmail);
        contentPane.add(new JLabel());
        contentPane.add(lblIban);
        contentPane.add(new JLabel());
        contentPane.add(lblNumarContract);
        contentPane.add(new JLabel());
        contentPane.add(lblDataAngajarii);
        contentPane.add(new JLabel());
        contentPane.add(lblFunctie);
        contentPane.add(new JLabel());
        contentPane.add(lblTipAdministrator);
        contentPane.add(new JLabel());

        add(contentPane);

        setVisible(true);
    }

    private void loadUserInfo() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM utilizatori WHERE ID_Utilizator = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUtilizator);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lblCnp.setText("CNP: " + resultSet.getString("CNP"));
                lblNume.setText("Nume: " + resultSet.getString("Nume"));
                lblPrenume.setText("Prenume: " + resultSet.getString("Prenume"));
                lblAdresa.setText("Adresa: " + resultSet.getString("Adresa"));
                lblTelefon.setText("Telefon: " + resultSet.getString("Telefon"));
                lblEmail.setText("Email: " + resultSet.getString("Email"));
                lblIban.setText("IBAN: " + resultSet.getString("IBAN"));
                lblNumarContract.setText("Numar Contract: " + resultSet.getInt("Numar_Contract"));
                lblDataAngajarii.setText("Data Angajarii: " + resultSet.getDate("Data_Angajarii"));
                lblFunctie.setText("Functie: " + resultSet.getString("Functie"));
                lblTipAdministrator.setText("Tip Administrator: " + resultSet.getString("TipAdministrator"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int utilizatorId = 1;
            new ViewOwnInfo(utilizatorId);
        });
    }
}

