package Service;

import Configuration.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacientView extends JFrame {

    private JTable pacientiTable;

    public PacientView() {
        setTitle("Pacienti View");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        pacientiTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(pacientiTable);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        loadPacientiData();
    }

    private void loadPacientiData() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM pacient";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("CNP");
            model.addColumn("Nume");
            model.addColumn("Prenume");
            model.addColumn("Istoric");

            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("ID"),
                        resultSet.getString("CNP"),
                        resultSet.getString("Nume"),
                        resultSet.getString("Prenume"),
                        resultSet.getInt("Istoric")
                };
                model.addRow(row);
            }

            pacientiTable.setModel(model);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PacientView().setVisible(true);
        });
    }
}
