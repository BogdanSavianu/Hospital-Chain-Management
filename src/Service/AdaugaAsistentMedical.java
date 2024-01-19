package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static Repository.UtilizatoriRepository.adaugaUtilizatorSiAsistentMedical;

public class AdaugaAsistentMedical extends JFrame {
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
    private JTextField tipAdministratorField;
    private JTextField salarField;
    private JTextField tipAsistentField;
    private JTextField gradField;
    private JTextField oreContractField;
    private JButton adaugaAsistentMedicalButton;
    private JButton backButton;

    private Integer id;

    public AdaugaAsistentMedical(JFrame parent, Integer id) {
        this.id = id;
        setTitle("Adauga Asistent Medical");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(parent);

        contentPane = new JPanel(new GridLayout(18, 2, 10, 10)); // Adjusted the grid layout for the new field
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
        tipAdministratorField = new JTextField();
        salarField = new JTextField();
        tipAsistentField = new JTextField();
        gradField = new JTextField();
        oreContractField = new JTextField();

        adaugaAsistentMedicalButton = new JButton("Adauga Asistent Medical");
        adaugaAsistentMedicalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaAsistentMedical();
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
        contentPane.add(tipAdministratorField);
        contentPane.add(new JLabel("Salar:"));
        contentPane.add(salarField);
        contentPane.add(new JLabel("Tip Asistent:"));
        contentPane.add(tipAsistentField);
        contentPane.add(new JLabel("Grad:"));
        contentPane.add(gradField);
        contentPane.add(new JLabel("Ore Contract:"));
        contentPane.add(oreContractField);
        contentPane.add(new JLabel());
        contentPane.add(adaugaAsistentMedicalButton);
        contentPane.add(new JLabel());
        contentPane.add(backButton);

        add(contentPane);

        setVisible(true);
    }

    private void adaugaAsistentMedical() {
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
        String tipAdministrator = tipAdministratorField.getText();
        double salar = Double.parseDouble(salarField.getText());
        String tipAsistent = tipAsistentField.getText();
        String grad = gradField.getText();
        int oreContract = Integer.parseInt(oreContractField.getText());

            try {
                int idAdd = adaugaUtilizatorSiAsistentMedical(cnp, nume, prenume, parola, adresa, telefon, email, iban, nrContract, dataAngajarii, functie, tipAdministrator, salar, tipAsistent, grad, oreContract);

                JOptionPane.showMessageDialog(this, "Asistent Medical adaugat cu succes!");
                dispose();
                AdaugaOrarAngajat adaugaOrarAngajat = new AdaugaOrarAngajat(this, idAdd, id);
                adaugaOrarAngajat.setVisible(true);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid numeric input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        dispose();
        new HomePageResurseUmane(id).setVisible(true);
    }

    private void goBack() {
        dispose();
        new HomePageResurseUmane(id).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdaugaAsistentMedical(null, 1).setVisible(true);
            }
        });
    }
}
