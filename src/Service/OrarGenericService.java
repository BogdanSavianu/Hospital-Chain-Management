package Service;

import Configuration.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class OrarGenericService {
    public static boolean verificaDisponibilitateMedic(int idMedic, String dataProgramare, int oraProgramare) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            LocalDate programareDate = LocalDate.parse(dataProgramare);

            Map<String, DayOfWeek> dayMap = new HashMap<>();
            dayMap.put("Luni", DayOfWeek.MONDAY);
            dayMap.put("Marti", DayOfWeek.TUESDAY);
            dayMap.put("Miercuri", DayOfWeek.WEDNESDAY);
            dayMap.put("Joi", DayOfWeek.THURSDAY);
            dayMap.put("Vineri", DayOfWeek.FRIDAY);
            dayMap.put("Sambata", DayOfWeek.SATURDAY);
            dayMap.put("Duminica", DayOfWeek.SUNDAY);

            String romanianDayName = programareDate.getDayOfWeek().name();



                String query = "SELECT * FROM orar_generic WHERE ID_angajat = ? AND zi = ? AND orar_generic.ora_incepere <= ? AND orar_generic.ora_sfarsire >= ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idMedic);
                preparedStatement.setString(2, romanianDayName);
                preparedStatement.setInt(3, oraProgramare);
                preparedStatement.setInt(4, oraProgramare);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    } else {
                        System.out.println("Medic is not available at the specified date or time.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
