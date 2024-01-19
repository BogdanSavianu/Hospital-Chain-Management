package Repository;

import Configuration.DatabaseConnection;
import Model.Angajat;
import Model.Medic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicRepository {
    public static int getLastMedicId() {
        int lastMedicId = -1;

        try{
            Connection connection = DatabaseConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{ CALL GetLastMedic() }");
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                lastMedicId = resultSet.getInt("ID_Angajat");
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return lastMedicId;
    }
    public static List<Medic> getAllMedics(){
        List<Medic> medics = new ArrayList<>();
        final String SELECT_ALL_MEDICI_QUERY = "SELECT * FROM medici";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEDICI_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Medic medic = new Medic(
                        resultSet.getInt("ID_Medic"),
                        resultSet.getInt("ID_Angajat"),
                        resultSet.getString("Specializare"),
                        resultSet.getString("Grad"),
                        resultSet.getString("Cod_Parafa"),
                        resultSet.getString("Competente"),
                        resultSet.getString("Titlu_Stiintific"),
                        resultSet.getDouble("Procent_Comision"),
                        resultSet.getString("Post_Didactic")
                );

                medics.add(medic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medics;
    }
    public static List<Medic> getMediciByServiciu(String numeServiciu) {
        List<Medic> medici = getAllMedics();
        List<Medic> filteredMedici = new ArrayList<>();

        numeServiciu = numeServiciu.trim();
        for (Medic medic : medici) {
            if (medic.getSpecializare() != null && medic.getSpecializare().trim().equals(numeServiciu)) {
                filteredMedici.add(medic);
            }
        }

        return filteredMedici;
    }

}
