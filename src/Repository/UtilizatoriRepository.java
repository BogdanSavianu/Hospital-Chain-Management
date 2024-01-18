package Repository;

import Configuration.DatabaseConnection;
import Model.Angajat;
import Model.Autentificare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizatoriRepository {
    public static void adaugaUtilizatorSiMedic(String cnp, String nume, String prenume, String adresa, String telefon, String email, String parola, String iban, int nrContract, String dataAngajarii, String functie, String tipAdministrator, double salar, String specializare, String grad, String parafa, String competente, String titluStiintific, float procentComision, String postDidactic, Integer oreContract) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String storedProcedureCall = "{call AdaugaUtilizatorSiMedic(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {

                callableStatement.setString(1, cnp);
                callableStatement.setString(2, nume);
                callableStatement.setString(3, prenume);
                callableStatement.setString(4, adresa);
                callableStatement.setString(5, telefon);
                callableStatement.setString(6, email);
                callableStatement.setString(7, parola);
                callableStatement.setString(8, iban);
                callableStatement.setInt(9, nrContract);
                callableStatement.setString(10, dataAngajarii);
                callableStatement.setString(11, functie);
                callableStatement.setString(12, tipAdministrator);
                callableStatement.setDouble(13, salar);
                callableStatement.setString(14, specializare);
                callableStatement.setString(15, grad);
                callableStatement.setString(16, parafa);
                callableStatement.setString(17, competente);
                callableStatement.setString(18, titluStiintific);
                callableStatement.setFloat(19, procentComision);
                callableStatement.setString(20, postDidactic);
                callableStatement.setInt(21, oreContract);

                callableStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int adaugaUtilizatorSiAsistentMedical(String cnp, String nume, String prenume, String parola, String adresa, String telefon, String email, String iban, int nrContract, String dataAngajarii, String functie, String tipAdministrator, double salar, String tipAsistent, String grad, Integer oreContract) {
        int asistentMedicalId = -1;

        try {
            Connection connection = DatabaseConnection.getConnection();
            String storedProcedureCall = "{call AdaugaUtilizatorSiAsistentMedical(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setString(1, cnp);
                callableStatement.setString(2, nume);
                callableStatement.setString(3, prenume);
                callableStatement.setString(4, adresa);
                callableStatement.setString(5, parola);
                callableStatement.setString(6, telefon);
                callableStatement.setString(7, email);
                callableStatement.setString(8, iban);
                callableStatement.setInt(9, nrContract);
                callableStatement.setString(10, dataAngajarii);
                callableStatement.setString(11, functie);
                callableStatement.setString(12, tipAdministrator);
                callableStatement.setDouble(13, salar);
                callableStatement.setString(14, tipAsistent);
                callableStatement.setString(15, grad);
                callableStatement.setInt(16, oreContract);

                callableStatement.execute();

                try (ResultSet generatedKeys = callableStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        asistentMedicalId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return asistentMedicalId;
    }


    public static int adaugaUtilizator(String cnp, String nume, String prenume, String adresa, String telefon, String email, String parola, String iban, int nrContract, String dataAngajarii, String functie, String tipAdministrator, Double salar, Integer oreContract) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String storedProcedureCall = "{call AdaugaUtilizator(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {

                callableStatement.setString(1, cnp);
                callableStatement.setString(2, nume);
                callableStatement.setString(3, prenume);
                callableStatement.setString(4, adresa);
                callableStatement.setString(5, telefon);
                callableStatement.setString(6, email);
                callableStatement.setString(7, parola);
                callableStatement.setString(8, iban);
                callableStatement.setInt(9, nrContract);
                callableStatement.setString(10, dataAngajarii);
                callableStatement.setString(11, functie);
                callableStatement.setString(12, tipAdministrator);
                callableStatement.setDouble(13, salar);
                callableStatement.setInt(14, oreContract);

                callableStatement.execute();

                ResultSet resultSet = callableStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }


    public static Autentificare getUser(int userId, String password) throws SQLException {
        Autentificare auth = null;

        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall("{call GetUserByIDAndPassword(?, ?)}")) {
            callableStatement.setInt(1, userId);
            callableStatement.setString(2, password);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    auth = new Autentificare();
                    auth.setIdUtilizator(resultSet.getInt(1));
                    auth.setParola(resultSet.getString(13));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return auth;
    }

    public static List<Angajat> getAllAngajati() {
        List<Angajat> angajati = new ArrayList<>();
        final String SELECT_ALL_ANGAJATI_QUERY = "SELECT * FROM angajati";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ANGAJATI_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Angajat angajat = new Angajat(
                        resultSet.getInt("ID_Angajat"),
                        resultSet.getInt("ID_Utilizator"),
                        resultSet.getDouble("Salariu_Negociat"),
                        resultSet.getInt("Ore_Realizate"),
                        resultSet.getInt("Ore_Contract"),
                        resultSet.getString("Tip_Functie"),
                        resultSet.getDate("Data_Inceput")
                );

                angajati.add(angajat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return angajati;
    }

    public static Angajat searchAngajat(int id) {
        Connection connection = DatabaseConnection.getConnection();
        List<Angajat> angajati = getAllAngajati();
        for (Angajat angajat : angajati) {
            if (angajat.getIdUtilizator() == id) {
                return angajat;
            }
        }
        return null;
    }
}
