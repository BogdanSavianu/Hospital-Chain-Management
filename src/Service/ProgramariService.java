package Service;

import Configuration.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class ProgramariService {
    public static void adaugaProgramare(Integer idReceptioner, String numePacient, String prenumePacient, Integer idMedic, String dataProgramare, Integer oraProgramare, String locatie, Integer idServiciu) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String storedProcedureCall = "{call AdaugaProgramare( ?, ?, ?, ?, ?, ?, ?, ?)}";

            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {

                callableStatement.setInt(1, idReceptioner);
                callableStatement.setString(2, numePacient);
                callableStatement.setString(3, prenumePacient);
                callableStatement.setInt(4, idMedic);
                callableStatement.setString(5, dataProgramare);
                callableStatement.setInt(6, oraProgramare);
                callableStatement.setString(7, locatie);
                callableStatement.setInt(8, idServiciu);

                callableStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
