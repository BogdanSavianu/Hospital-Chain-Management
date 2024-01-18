package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Service.ProgramariService.adaugaProgramare;

public class ProgramarePage extends JFrame {
    private JPanel contentPane;
    private JTextField receptionerField;
    private JTextField pacientField;
    private JTextField numePacientField;
    private JTextField prenumePacientField;
    private JTextField medicField;
    private JTextField dataField;
    private JTextField oraField;
    private JTextField locatieField;
    private JTextField serviciuField;
    private JButton adaugaProgramareButton;
    private JButton backButton;

    private int idReceptioner;  // New field to store the idReceptioner

    // Modified constructor to receive the idReceptioner
    public ProgramarePage(JFrame parentFrame, int idReceptioner) {
        this.idReceptioner = idReceptioner;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Adauga Programare");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(11, 2)); // Increased rows for the Back button

        receptionerField = new JTextField();
        numePacientField = new JTextField();
        prenumePacientField = new JTextField();
        medicField = new JTextField();
        dataField = new JTextField();
        oraField = new JTextField();
        locatieField = new JTextField();
        serviciuField = new JTextField();

        adaugaProgramareButton = new JButton("Adauga Programare");
        adaugaProgramareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProgramare();
            }
        });

        backButton = new JButton("Back"); // Added Back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        // Auto-fill the receptionerField
        receptionerField.setText(String.valueOf(idReceptioner));

        contentPane.add(new JLabel("ID Receptioner:"));
        contentPane.add(receptionerField);
        contentPane.add(new JLabel("Nume Pacient:"));
        contentPane.add(numePacientField);
        contentPane.add(new JLabel("Prenume Pacient:"));
        contentPane.add(prenumePacientField);
        contentPane.add(new JLabel("ID Medic:"));
        contentPane.add(medicField);
        contentPane.add(new JLabel("Data Programare:"));
        contentPane.add(dataField);
        contentPane.add(new JLabel("Ora Programare:"));
        contentPane.add(oraField);
        contentPane.add(new JLabel("Locatie:"));
        contentPane.add(locatieField);
        contentPane.add(new JLabel("ID Serviciu:"));
        contentPane.add(serviciuField);
        contentPane.add(new JLabel()); // Placeholder for spacing
        contentPane.add(adaugaProgramareButton);
        contentPane.add(new JLabel()); // Placeholder for spacing
        contentPane.add(backButton); // Added Back button

        add(contentPane);

        setVisible(true);
    }

    private void addProgramare() {
        // Retrieve data from text fields
        int idReceptioner = Integer.parseInt(receptionerField.getText());
        String numePacient = numePacientField.getText();
        String prenumePacient = prenumePacientField.getText();
        int idMedic = Integer.parseInt(medicField.getText());
        String dataProgramare = dataField.getText();
        int oraProgramare = Integer.parseInt(oraField.getText());
        String locatie = locatieField.getText();
        int idServiciu = Integer.parseInt(serviciuField.getText());

        if (OrarGenericService.verificaDisponibilitateMedic(idMedic, dataProgramare, oraProgramare)) {
            adaugaProgramare(idReceptioner, numePacient, prenumePacient, idMedic, dataProgramare, oraProgramare, locatie, idServiciu);

            JOptionPane.showMessageDialog(this, "Programare adaugata cu succes!");
        } else {
            JOptionPane.showMessageDialog(this, "Medicul nu este disponibil la ora specificata.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.dispose();
        new HomePage(idReceptioner).setVisible(true);  // Pass idReceptioner to the new instance of HomePage
    }

    private void goBack() {
        dispose();
        new HomePage(idReceptioner).setVisible(true);  // Pass idReceptioner to the new instance of HomePage
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int receptionistId = 123;  // Replace with the actual id of the receptionist
                new ProgramarePage(new HomePage(receptionistId), receptionistId);
            }
        });
    }
}
