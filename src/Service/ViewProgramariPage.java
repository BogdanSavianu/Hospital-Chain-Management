package Service;

import Configuration.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ViewProgramariPage extends JFrame {
    private JPanel contentPane;
    private JTable appointmentsTable;

    public ViewProgramariPage(int idReceptioner) {
        initializeComponents(idReceptioner);
    }

    private void initializeComponents(int idReceptioner) {
        setTitle("View All Appointments");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Vector<Vector<Object>> data = getAppointmentsData(idReceptioner);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID Programare");
        columnNames.add("ID Receptioner");
        columnNames.add("ID Pacient");
        columnNames.add("ID Medic");
        columnNames.add("Data Programare");
        columnNames.add("Ora Programare");
        columnNames.add("Durata Consulta");
        columnNames.add("Locatie");
        columnNames.add("ID Serviciu");
        columnNames.add("Nume Pacient");
        columnNames.add("Prenume Pacient");

        appointmentsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(appointmentsTable);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        setContentPane(contentPane);
        setVisible(true);
    }

    private Vector<Vector<Object>> getAppointmentsData(int idReceptioner) {
        Vector<Vector<Object>> data = new Vector<>();

        try {
            Connection connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM programari";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Vector<Object> row = new Vector<>();
                        row.add(resultSet.getInt("ID_Programare"));
                        row.add(resultSet.getInt("ID_Receptioner"));
                        row.add(resultSet.getInt("ID_Pacient"));
                        row.add(resultSet.getInt("ID_Medic"));
                        row.add(resultSet.getString("Data_Programare"));
                        row.add(resultSet.getInt("Ora_Programare"));
                        row.add(resultSet.getInt("Durata_Consulta"));
                        row.add(resultSet.getString("Locatie"));
                        row.add(resultSet.getInt("ID_Serviciu"));
                        row.add(resultSet.getString("Nume_Pacient"));
                        row.add(resultSet.getString("Prenume_Pacient"));
                        data.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int receptionistId = 123;
                new ViewProgramariPage(receptionistId);
            }
        });
    }
}
