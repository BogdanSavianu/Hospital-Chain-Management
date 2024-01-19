package Service;

import Model.Medic;
import Repository.MedicRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Repository.UtilizatoriRepository.adaugaUtilizatorSiMedic;

public class AdaugaMedic extends JFrame {
    private JPanel contentPane;
    private JTextField cnpField;
    private JTextField numeField;
    private JTextField prenumeField;
    private JTextField adresaField;
    private JTextField telefonField;
    private JTextField emailField;
    private JTextField parolaField;
    private JTextField ibanField;
    private JTextField nrContractField;
    private JTextField dataAngajariiField;
    private JTextField functieField;
    private JComboBox<String> tipAdministratorComboBox;
    private JTextField salarField;
    private JTextField specializareField;
    private JComboBox<String> gradComboBox;
    private JTextField parafaField;
    private JTextField competenteField;
    private JComboBox<String> titluStiintificComboBox;
    private JTextField procentComisionField;
    private JComboBox<String> postDidacticComboBox;
    private JTextField oreContractField;
    private JButton adaugaMedicButton;
    private JButton backButton;

    private Integer id;

    public AdaugaMedic(JFrame parent, Integer id) {
        this.id = id;
        setTitle("Adauga Medic");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(parent);

        contentPane = new JPanel(new GridLayout(23, 2, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cnpField = new JTextField();
        numeField = new JTextField();
        prenumeField = new JTextField();
        adresaField = new JTextField();
        telefonField = new JTextField();
        emailField = new JTextField();
        parolaField = new JTextField();
        ibanField = new JTextField();
        nrContractField = new JTextField();
        dataAngajariiField = new JTextField();

        // JComboBox for enum fields
        functieField = new JTextField();
        functieField.setText("Medic");

        String[] tipAdministratorOptions = {"Administrator", "SuperAdministrator"};
        tipAdministratorComboBox = new JComboBox<>(tipAdministratorOptions);

        salarField = new JTextField();
        specializareField = new JTextField();

        String[] gradOptions = {"Specialist", "Primar"};
        gradComboBox = new JComboBox<>(gradOptions);

        parafaField = new JTextField();
        competenteField = new JTextField();

        String[] titluStiintificOptions = {"Doctorand", "Doctor in stiinte medicale"};
        titluStiintificComboBox = new JComboBox<>(titluStiintificOptions);

        procentComisionField = new JTextField();

        String[] postDidacticOptions = {"Preparator", "Asistent", "Lector", "Conferentiar", "Profesor"};
        postDidacticComboBox = new JComboBox<>(postDidacticOptions);

        oreContractField = new JTextField();

        adaugaMedicButton = new JButton("Adauga Medic");
        adaugaMedicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaMedic();
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
        contentPane.add(new JLabel("Telefon:"));
        contentPane.add(telefonField);
        contentPane.add(new JLabel("Email:"));
        contentPane.add(emailField);
        contentPane.add(new JLabel("Parola:"));
        contentPane.add(parolaField);
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
        contentPane.add(new JLabel("Specializare:"));
        contentPane.add(specializareField);
        contentPane.add(new JLabel("Grad:"));
        contentPane.add(gradComboBox);
        contentPane.add(new JLabel("Parafa:"));
        contentPane.add(parafaField);
        contentPane.add(new JLabel("Competente:"));
        contentPane.add(competenteField);
        contentPane.add(new JLabel("Titlu Stiintific:"));
        contentPane.add(titluStiintificComboBox);
        contentPane.add(new JLabel("Procent Comision:"));
        contentPane.add(procentComisionField);
        contentPane.add(new JLabel("Post Didactic:"));
        contentPane.add(postDidacticComboBox);
        contentPane.add(new JLabel("Ore Contract:"));
        contentPane.add(oreContractField);
        contentPane.add(new JLabel());
        contentPane.add(adaugaMedicButton);
        contentPane.add(new JLabel());
        contentPane.add(backButton);

        add(contentPane);

        setVisible(true);
    }

    private void adaugaMedic() {
        String cnp = cnpField.getText();
        String nume = numeField.getText();
        String prenume = prenumeField.getText();
        String adresa = adresaField.getText();
        String telefon = telefonField.getText();
        String email = emailField.getText();
        String parola = parolaField.getText();
        String iban = ibanField.getText();
        int nrContract = Integer.parseInt(nrContractField.getText());
        String dataAngajarii = dataAngajariiField.getText();
        String functie = functieField.getText();
        String tipAdministrator = tipAdministratorComboBox.getSelectedItem().toString();
        double salar = Double.parseDouble(salarField.getText());
        String specializare = specializareField.getText();
        String grad = gradComboBox.getSelectedItem().toString();
        String parafa = parafaField.getText();
        String competente = competenteField.getText();
        String titluStiintific = titluStiintificComboBox.getSelectedItem().toString();
        float procentComision = Float.parseFloat(procentComisionField.getText());
        String postDidactic = postDidacticComboBox.getSelectedItem().toString();
        int oreContract = Integer.parseInt(oreContractField.getText());

        try {
            adaugaUtilizatorSiMedic(cnp, nume, prenume, adresa, telefon, email, parola, iban, nrContract, dataAngajarii, functie, tipAdministrator, salar, specializare, grad, parafa, competente, titluStiintific, procentComision, postDidactic, oreContract);

            JOptionPane.showMessageDialog(this, "Medic adaugat cu succes!");

            dispose();
            Integer idMedic = MedicRepository.getLastMedicId();
            AdaugaOrarAngajat adaugaOrarAngajat = new AdaugaOrarAngajat(this, idMedic, id);
            adaugaOrarAngajat.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid numeric input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                new AdaugaMedic(null, 1).setVisible(true);
            }
        });
    }
}
