package Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static Repository.AngajatRepository.adaugaOrarAngajat;

public class AdaugaOrarAngajat extends JFrame {
    private JPanel contentPane;
    private JTable scheduleTable;
    private JButton adaugaOrarButton;
    private JButton backButton;

    private int idResurseUmane;
    private int idAngajat;
    public AdaugaOrarAngajat(JFrame parent, int idAngajat, Integer idResurseUmane) {
        this.idAngajat = idAngajat;
        this.idResurseUmane = idResurseUmane;

        setTitle("Adauga Orar Angajat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(parent);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = {"Ziua Saptamanii", "Ora Inceput", "Ora Sfarsit", "Locatia"};
        Object[][] data = {
                {"Luni", "", "", ""},
                {"Marti", "", "", ""},
                {"Miercuri", "", "", ""},
                {"Joi", "", "", ""},
                {"Vineri", "", "", ""},
                {"Sambata", "", "", ""},
                {"Duminica", "", "", ""}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        scheduleTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        adaugaOrarButton = new JButton("Adauga Orar");
        backButton = new JButton("Back");

        adaugaOrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaOrar();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(adaugaOrarButton);
        buttonPanel.add(backButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPane);

        setVisible(true);
    }

    private void adaugaOrar() {
        DefaultTableModel model = (DefaultTableModel) scheduleTable.getModel();
        Vector<Vector<String>> data = new Vector<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            Vector<String> row = new Vector<>();
            for (int j = 0; j < model.getColumnCount(); j++) {
                row.add(model.getValueAt(i, j).toString());
            }
            data.add(row);
        }


        try {
            for (Vector<String> row : data) {
                String ziuaSaptamanii = row.get(0);
                String oraInceput = row.get(1);
                String oraSfarsit = row.get(2);
                String locatia = row.get(3);

                if (!oraInceput.isEmpty() && !oraSfarsit.isEmpty()) {
                    adaugaOrarAngajat(idAngajat, ziuaSaptamanii, oraInceput, oraSfarsit, locatia, "generic");
                }
            }

            JOptionPane.showMessageDialog(this, "Orar adaugat cu succes!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        dispose();
        new HomePageResurseUmane(idResurseUmane).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdaugaOrarAngajat(null, 1, 1).setVisible(true);
            }
        });
    }
}
