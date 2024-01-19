package Repository;

import Configuration.DatabaseConnection;
import Model.ServiciuMedical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiciiMedicaleRepository {
    public static List<ServiciuMedical> getAllSpecializari() {
        List<ServiciuMedical> specializari = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID_Serviciu, Nume_Serviciu, Specializare, Pret, Durata_Minute FROM servicii_medicale");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ServiciuMedical specializare = new ServiciuMedical(
                        resultSet.getInt("ID_Serviciu"),
                        resultSet.getString("Nume_Serviciu"),
                        resultSet.getString("Specializare"),
                        resultSet.getDouble("Pret"),
                        resultSet.getInt("Durata_Minute")
                );
                specializari.add(specializare);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specializari;
    }

}
