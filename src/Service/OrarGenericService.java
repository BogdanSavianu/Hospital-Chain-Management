package Service;

import Configuration.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class OrarGenericService {
    private static String getRomanianDayName(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return "Luni";
            case TUESDAY:
                return "Marti";
            case WEDNESDAY:
                return "Miercuri";
            case THURSDAY:
                return "Joi";
            case FRIDAY:
                return "Vineri";
            case SATURDAY:
                return "Sambata";
            case SUNDAY:
                return "Duminica";
            default:
                throw new IllegalArgumentException("Invalid dayOfWeek: " + dayOfWeek);
        }
    }

    public static boolean verificaDisponibilitateMedic(int idMedic, String dataProgramare, int oraProgramare) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            LocalDate programareDate = LocalDate.parse(dataProgramare);

            String romanianDayName = getRomanianDayName(programareDate.getDayOfWeek());

            if (isMedicOnVacation(idMedic, programareDate)) {
                showPopup("Medic is on vacation on the specified date.");
                return false;
            }

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
                        showPopup("Medic is not available at the specified date or time.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isMedicOnVacation(int idMedic, LocalDate date) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM concedii WHERE ID_Angajat = ? AND ? BETWEEN Data_Inceput AND Data_Sfarsit";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idMedic);
                preparedStatement.setDate(2, java.sql.Date.valueOf(date));

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
