package Service;

import Configuration.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ProfitCalculator {

    public static class ProfitResult {
        private final BigDecimal totalSalaries;
        private final BigDecimal totalServicePrices;
        private final BigDecimal profit;

        public ProfitResult(BigDecimal totalSalaries, BigDecimal totalServicePrices, BigDecimal profit) {
            this.totalSalaries = totalSalaries;
            this.totalServicePrices = totalServicePrices;
            this.profit = profit;
        }

        public BigDecimal getTotalSalaries() {
            return totalSalaries;
        }

        public BigDecimal getTotalServicePrices() {
            return totalServicePrices;
        }

        public BigDecimal getProfit() {
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

                BigDecimal totalSalaries = statement.getBigDecimal(1);
                BigDecimal totalServicePrices = statement.getBigDecimal(2);
                BigDecimal profit = statement.getBigDecimal(3);

                return new ProfitResult(totalSalaries, totalServicePrices, profit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BigDecimal calculateMedicProfit(int medicId, int month, int year) {
        BigDecimal profit = BigDecimal.ZERO;

        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "{CALL CalculeazaProfitMedic(?, ?, ?, ?)}";
            try (CallableStatement statement = connection.prepareCall(sql)) {
                statement.setInt(1, medicId);
                statement.setInt(2, month);
                statement.setInt(3, year);
                statement.registerOutParameter(4, Types.DECIMAL);

                statement.execute();

                profit = statement.getBigDecimal(4);
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
