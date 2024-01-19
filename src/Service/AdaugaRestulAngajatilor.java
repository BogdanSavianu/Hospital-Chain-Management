package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Repository.UtilizatoriRepository.adaugaUtilizator;

public class AdaugaRestulAngajatilor extends JFrame {
    private JPanel contentPane;
    private JTextField cnpField;
    private JTextField numeField;
    private JTextField prenumeField;
    private JTextField adresaField;
    private JTextField parolaField;
    private JTextField telefonField;
    private JTextField emailField;
    private JTextField ibanField;
    private JTextField nrContractField;
    private JTextField dataAngajariiField;
    private JTextField functieField;
    private JComboBox<String> tipAdministratorComboBox;
    private JTextField salarField;
    private JTextField oreContractField;
    private JButton adaugaAngajatButton;
    private JButton backButton;

    private Integer id;

    private String angajatType;

    public AdaugaRestulAngajatilor(JFrame parent, String angajatType, Integer idResurseUmane) {
        this.angajatType = angajatType;
        this.id = idResurseUmane;
        setTitle("Adauga " + angajatType);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(parent);

        contentPane = new JPanel(new GridLayout(16, 2, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cnpField = new JTextField();
        numeField = new JTextField();
        prenumeField = new JTextField();
        adresaField = new JTextField();
        parolaField = new JTextField();
        telefonField = new JTextField();
        emailField = new JTextField();
        ibanField = new JTextField();
        nrContractField = new JTextField();
        dataAngajariiField = new JTextField();
        functieField = new JTextField();
        tipAdministratorComboBox = new JComboBox<>(new String[]{"Administrator", "SuperAdministrator"});
        tipAdministratorComboBox.setSelectedIndex(0);
        salarField = new JTextField();
        oreContractField = new JTextField();

        functieField.setText(angajatType);

        adaugaAngajatButton = new JButton("Adauga " + angajatType);
        adaugaAngajatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaAngajat();
            }
        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        contentPane.add(new JLabel("CNP:"));
        contentPane.add(cnpField);
        contentPane.add(new JLabel("Nume:"));
        contentPane.add(numeField);
        contentPane.add(new JLabel("Prenume:"));
        contentPane.add(prenumeField);
        contentPane.add(new JLabel("Adresa:"));
        contentPane.add(adresaField);
        contentPane.add(new JLabel("Parola:"));
        contentPane.add(parolaField);
        contentPane.add(new JLabel("Telefon:"));
        contentPane.add(telefonField);
        contentPane.add(new JLabel("Email:"));
        contentPane.add(emailField);
        contentPane.add(new JLabel("IBAN:"));
        contentPane.add(ibanField);
        contentPane.add(new JLabel("Numar Contract:"));
        contentPane.add(nrContractField);
        contentPane.add(new JLabel("Data Angajarii:"));
        contentPane.add(dataAngajariiField);
        contentPane.add(new JLabel("Functie:"));
        contentPane.add(functieField);
        contentPane.add(new JLabel("Tip Administrator:"));
        contentPane.add(tipAdministratorComboBox);
        contentPane.add(new JLabel("Salar:"));
        contentPane.add(salarField);
        contentPane.add(new JLabel("Ore Contract:"));
        contentPane.add(oreContractField);
        contentPane.add(new JLabel());
        contentPane.add(adaugaAngajatButton);
        contentPane.add(new JLabel());
        contentPane.add(backButton);

        add(contentPane);

        setVisible(true);
    }

    private void adaugaAngajat() {
        String cnp = cnpField.getText();
        String nume = numeField.getText();
        String prenume = prenumeField.getText();
        String adresa = adresaField.getText();
        String parola = parolaField.getText();
        String telefon = telefonField.getText();
        String email = emailField.getText();
        String iban = ibanField.getText();
        int nrContract = Integer.parseInt(nrContractField.getText());
        String dataAngajarii = dataAngajariiField.getText();
        String functie = functieField.getText();
        String tipAdministrator = tipAdministratorComboBox.getSelectedItem().toString();
        double salar = Double.parseDouble(salarField.getText());
        int oreContract = Integer.parseInt(oreContractField.getText());

        try {
            int angajatId = adaugaUtilizator(cnp, nume, prenume, adresa, telefon, email, parola, iban, nrContract, dataAngajarii, functie, tipAdministrator, salar, oreContract);

            JOptionPane.showMessageDialog(this, angajatType + " adaugat cu succes!");

            dispose();
            AdaugaOrarAngajat adaugaOrarAngajat = new AdaugaOrarAngajat(this, angajatId, id);
            adaugaOrarAngajat.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid numeric input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        dispose();
        new AdaugaAngajat(null, id).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdaugaRestulAngajatilor(null, "Tip Angajat", 1).setVisible(true);
            }
        });
    }
}
