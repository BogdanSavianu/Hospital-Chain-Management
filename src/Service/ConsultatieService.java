package Service;

import Configuration.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class ConsultatieService {
    public static void adaugaConsultatie(Integer idPacient, String cnpPacient, Integer idAsistentMedical, Integer idProgramare, String simptome, String investigatii, String diagnostic, String recomandari, String parafa) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String storedProcedureCall = "{call AdaugaRaportMedical(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {

                callableStatement.setInt(1, idPacient);
                callableStatement.setString(2,cnpPacient);
                callableStatement.setInt(3, idAsistentMedical);
                callableStatement.setInt(4, idProgramare);
                callableStatement.setString(5, simptome);
                callableStatement.setString(6, investigatii);
                callableStatement.setString(7, diagnostic);
                callableStatement.setString(8, recomandari);
                callableStatement.setString(9, parafa);

                callableStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}