package Repository;

import Configuration.DatabaseConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AngajatRepository {
    public static void adaugaOrarAngajat(int idAngajat, String ziuaSaptamanii, String oraInceput, String oraSfarsit, String locatia, String tipOrar) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String query;

            if ("generic".equals(tipOrar)) {
                query = "CALL AdaugaOrarPentruAngajat(?, ?, ?, ?, ?, 'generic')";
            } else if ("specific".equals(tipOrar)) {
                query = "CALL AdaugaOrarPentruAngajat(?, ?, ?, ?, ?, 'specific')";
            } else {
                // Handle invalid tipOrar value
                throw new IllegalArgumentException("Invalid tipOrar value: " + tipOrar);
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idAngajat);
            preparedStatement.setString(2, ziuaSaptamanii);
            preparedStatement.setString(3, oraInceput);
            preparedStatement.setString(4, oraSfarsit);
            preparedStatement.setString(5, locatia);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception according to your needs
        } finally {
            // Close resources in a finally block
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle or log the exception according to your needs
            }
        }
    }
}
