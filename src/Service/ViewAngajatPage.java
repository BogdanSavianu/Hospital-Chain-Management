package Service;

import Model.Angajat;
import Repository.UtilizatoriRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAngajatPage extends JFrame {
    private JPanel contentPane;
    private JTextField angajatIdField;
    private JButton searchButton;
    private JButton backButton;
    private Integer id;

    public ViewAngajatPage(Integer id) {
        this.id = id;
        setTitle("Search Angajat");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new GridLayout(4, 1, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        angajatIdField = new JTextField();
        searchButton = new JButton("Search");
        backButton = new JButton("Back");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAndDisplay();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        contentPane.add(new JLabel("Enter Angajat ID:"));
        contentPane.add(angajatIdField);
        contentPane.add(searchButton);
        contentPane.add(backButton);  // Adding Back button

        add(contentPane);
        setVisible(true);
    }

    private void searchAndDisplay() {
        try {
            int angajatId = Integer.parseInt(angajatIdField.getText());
            Angajat angajat = UtilizatoriRepository.searchAngajat(angajatId);

            if (angajat != null) {
                // Display Angajat details in a pop-up window
                String resultText = "ID Angajat: " + angajat.getIdAngajat() + "\n"
                        + "ID Utilizator: " + angajat.getIdUtilizator() + "\n"
                        + "Salariu Negociat: " + angajat.getSalariuNegociat() + "\n"
                        + "Ore Realizate: " + angajat.getOreRealizate() + "\n"
                        + "Ore Contract: " + angajat.getOreContract() + "\n"
                        + "Tip Functie: " + angajat.getTipFunctie() + "\n"
                        + "Data Angajare: " + angajat.getDataAngajare();

                JOptionPane.showMessageDialog(this, resultText, "Angajat Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Angajat not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid numeric Angajat ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        dispose();
        new HomePageResurseUmane(id).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewAngajatPage(1);
            }
        });
    }
}
