package Service;

import Configuration.DatabaseConnection;

import java.sql.*;
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
    public static Integer getServiciuIdForConsultatie(Integer idConsultatie) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            // Query to get the ID_Programare for the given ID_Consult
            String getProgramareIdQuery = "SELECT ID_Programare FROM consultatii WHERE ID_Raport = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(getProgramareIdQuery)) {
                preparedStatement.setInt(1, idConsultatie);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int idProgramare = resultSet.getInt("ID_Programare");

                        // Query to get the ID_Serviciu for the found ID_Programare
                        String getServiciuIdQuery = "SELECT ID_Serviciu FROM programari WHERE ID_Programare = ?";
                        try (PreparedStatement serviciuPreparedStatement = connection.prepareStatement(getServiciuIdQuery)) {
                            serviciuPreparedStatement.setInt(1, idProgramare);

                            try (ResultSet serviciuResultSet = serviciuPreparedStatement.executeQuery()) {
                                if (serviciuResultSet.next()) {
                                    return serviciuResultSet.getInt("ID_Serviciu");
                                }
                            }
                        }
                    }
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void adaugaBonForConsultatie(Integer idConsultatie) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            Integer idServiciu = ConsultatieService.getServiciuIdForConsultatie(idConsultatie);

            if (idServiciu != null) {
                String insertBonQuery = "INSERT INTO bonuri (ID_Consult, ID_Serviciu) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertBonQuery)) {
                    preparedStatement.setInt(1, idConsultatie);
                    preparedStatement.setInt(2, idServiciu);

                    preparedStatement.executeUpdate();
                }
                System.out.println("Bon added successfully.");
            } else {
                System.out.println("Unable to find ID_Serviciu for the given ID_Consultatie.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}