package Repository;

import Configuration.DatabaseConnection;
import Model.Medic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
