package Service;

import Configuration.DatabaseConnection;
import Model.Angajat;
import Repository.UtilizatoriRepository;

import java.sql.*;
import java.util.Calendar;
import java.util.Vector;

public class SalaryService {
    static Object[][] calculateSalaries(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Vector<Vector<Object>> salaryData = new Vector<>();

        try {
            connection.setAutoCommit(false);
            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH) + 1;
            int currentYear = calendar.get(Calendar.YEAR);

            for (int i = 0; i < 6; i++) {
                calendar.add(Calendar.MONTH, -1);
                int previousMonth = calendar.get(Calendar.MONTH) + 1;
                int previousYear = calendar.get(Calendar.YEAR);

                Double salary = calculateSalaryForMonth(id, previousMonth, previousYear);

                Vector<Object> row = new Vector<>();
                row.add(getMonthName(previousMonth));
                row.add(previousYear);
                row.add(salary);
                salaryData.add(row);
            }

            Object[][] dataArray = new Object[salaryData.size()][];
            for (int i = 0; i < salaryData.size(); i++) {
                dataArray[i] = salaryData.get(i).toArray();
            }

            connection.commit();
            return dataArray;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
    }


    private static Double calculateSalaryForMonth(int id, int month, int year) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try {
            connection.setAutoCommit(false);
            Angajat angajat = UtilizatoriRepository.searchAngajat(id);
            Double oreRealizate = Double.valueOf(angajat.getOreRealizate());
            Double oreContract = Double.valueOf(angajat.getOreContract());
            if (!angajat.getTipFunctie().equals("Medic")) {
                Double salarNegociat = angajat.getSalariuNegociat();
                connection.commit();
                return Double.valueOf(salarNegociat) * Double.valueOf(oreRealizate) / Double.valueOf(oreContract);
            }
            else{
                return calculateSalaryForMedic(angajat.getIdUtilizator());
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }

    }


    //TODO BONUS DE 0.5
    private static Double calculateSalaryForMedic(int id) throws SQLException {
        String storedProcedureCall = "{CALL SalarMedic(?)}";
        Connection connection = DatabaseConnection.getConnection();

        try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
            callableStatement.setInt(1, id);

            boolean hasResults = callableStatement.execute();

            if (hasResults) {
                try (ResultSet resultSet = callableStatement.getResultSet()) {
                    if (resultSet.next()) {
                        return resultSet.getDouble(1);
                    }
                }
            }
            return null;
        }
    }



    private static String getMonthName(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return monthNames[month - 1];
    }
}
