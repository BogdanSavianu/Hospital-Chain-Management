package Service;

import Configuration.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ProfitCalculator {

    public static class ProfitResult {
        private final Double totalSalaries;
        private final Double totalServicePrices;
        private final Double profit;

        public ProfitResult(Double totalSalaries, Double totalServicePrices, Double profit) {
            this.totalSalaries = totalSalaries;
            this.totalServicePrices = totalServicePrices;
            this.profit = profit;
        }

        public Double getTotalSalaries() {
            return totalSalaries;
        }

        public Double getTotalServicePrices() {
            return totalServicePrices;
        }

        public Double getProfit() {
            return profit;
        }
    }

    public static ProfitResult calculateProfit() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "{CALL CalculeazaProfitPoliclinica(?, ?, ?)}";
            try (CallableStatement statement = connection.prepareCall(sql)) {
                statement.registerOutParameter(1, Types.DECIMAL);
                statement.registerOutParameter(2, Types.DECIMAL);
                statement.registerOutParameter(3, Types.DECIMAL);

                statement.execute();

                Double totalSalaries = statement.getDouble(1);
                Double totalServicePrices = statement.getDouble(2);
                Double profit = statement.getDouble(3);

                return new ProfitResult(totalSalaries, totalServicePrices, profit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Double calculateMedicProfit(int medicId, int month, int year) {
        Double profit = 0.0;

        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "{CALL CalculeazaProfitMedic(?, ?, ?, ?)}";
            try (CallableStatement statement = connection.prepareCall(sql)) {
                statement.setInt(1, medicId);
                statement.setInt(2, month);
                statement.setInt(3, year);
                statement.registerOutParameter(4, Types.DECIMAL);

                statement.execute();

                profit = statement.getDouble(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profit;
    }

    public static void main(String[] args) {
        ProfitResult result = calculateProfit();
        if (result != null) {
            System.out.println("Total Salaries: " + result.getTotalSalaries());
            System.out.println("Total Service Prices: " + result.getTotalServicePrices());
            System.out.println("Profit: " + result.getProfit());
        }
    }
}
