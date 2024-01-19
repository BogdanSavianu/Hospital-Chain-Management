package Service;

import Configuration.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewConsultatii extends JFrame {

    private JTable consultatiiTable;

    public ViewConsultatii() {
        setTitle("Consultatii View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        consultatiiTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(consultatiiTable);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        loadConsultatiiData();
    }

    private void loadConsultatiiData() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM consultatii";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID_Raport");
            model.addColumn("ID_Asistent_Medical");
            model.addColumn("ID_Medic");
            model.addColumn("ID_Pacient");
            model.addColumn("Data_Consultatie");
            model.addColumn("Istoric");
            model.addColumn("Simptome");
            model.addColumn("Investigatii");
            model.addColumn("Diagnostic");
            model.addColumn("Recomandari");
            model.addColumn("Parafa");
            model.addColumn("ID_Programare");

            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("ID_Raport"),
                        resultSet.getInt("ID_Asistent_Medical"),
                        resultSet.getInt("ID_Medic"),
                        resultSet.getInt("ID_Pacient"),
                        resultSet.getDate("Data_Consultatie"),
                        resultSet.getString("Istoric"),
                        resultSet.getString("Simptome"),
                        resultSet.getString("Investigatii"),
                        resultSet.getString("Diagnostic"),
                        resultSet.getString("Recomandari"),
                        resultSet.getString("Parafa"),
                        resultSet.getInt("ID_Programare")
                };
                model.addRow(row);
            }

            consultatiiTable.setModel(model);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewConsultatii().setVisible(true);
        });
    }
}

