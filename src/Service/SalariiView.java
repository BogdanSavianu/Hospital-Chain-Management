package Service;

import Model.Angajat;
import Repository.UtilizatoriRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import static Service.ProfitCalculator.calculateMedicProfit;

public class SalariiView extends JFrame {
    private JPanel contentPane;
    private JTable salariesTable;

    private int id;

    public SalariiView(int id) {
        this.id = id;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Salarii View");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = {"Month", "Year", "Salar Calculat"};
        Object[][] data = new Object[0][];
        try {
            data = SalaryService.calculateSalaries(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        salariesTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(salariesTable);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        add(contentPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SalariiView(24));
    }
}

