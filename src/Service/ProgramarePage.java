package Service;

import Model.Medic;
import Model.ServiciuMedical;
import Repository.MedicRepository;
import Repository.ServiciiMedicaleRepository;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static Service.ProgramariService.adaugaProgramare;

public class ProgramarePage extends JFrame {
    private JPanel contentPane;
    private JTextField receptionerField;
    private JTextField pacientField;
    private JTextField numePacientField;
    private JTextField prenumePacientField;
    private JComboBox<Medic> medicComboBox;
    private JTextField dataField;
    private JTextField oraField;
    private JTextField locatieField;
    private JTextField serviciuField;
    private JButton adaugaProgramareButton;
    private JButton backButton;

    private int idReceptioner;

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
        contentPane.setLayout(new GridLayout(11, 2));

        receptionerField = new JTextField();
        numePacientField = new JTextField();
        prenumePacientField = new JTextField();
        medicComboBox = new JComboBox<>();
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

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        receptionerField.setText(String.valueOf(idReceptioner));

        contentPane.add(new JLabel("ID Receptioner:"));
        contentPane.add(receptionerField);
        contentPane.add(new JLabel("Nume Pacient:"));
        contentPane.add(numePacientField);
        contentPane.add(new JLabel("Prenume Pacient:"));
        contentPane.add(prenumePacientField);
        contentPane.add(new JLabel("Medic:"));
        contentPane.add(medicComboBox);
        contentPane.add(new JLabel("Data Programare:"));
        contentPane.add(dataField);
        contentPane.add(new JLabel("Ora Programare:"));
        contentPane.add(oraField);
        contentPane.add(new JLabel("Locatie:"));
        contentPane.add(locatieField);
        contentPane.add(new JLabel("ID Serviciu:"));
        contentPane.add(serviciuField);
        contentPane.add(adaugaProgramareButton);
        contentPane.add(backButton);

        add(contentPane);

        serviciuField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                populateMedicComboBox();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                populateMedicComboBox();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                populateMedicComboBox();
            }
        });

        populateMedicComboBox();

        setVisible(true);
    }

    private void populateMedicComboBox() {
        DefaultComboBoxModel<Medic> model = new DefaultComboBoxModel<>();

        try {
            String idServiciuText = serviciuField.getText().trim();

            if (!idServiciuText.isEmpty()) {
                int idServiciu = Integer.parseInt(idServiciuText);

                List<ServiciuMedical> servicii = ServiciiMedicaleRepository.getAllSpecializari();
                String serviciu = null;
                for (ServiciuMedical it : servicii)
                    if (it.getIdServiciu() == idServiciu)
                        serviciu = it.getSpecialitate();

                List<Medic> medici = MedicRepository.getMediciByServiciu(serviciu);
                for (Medic medic : medici) {
                    model.addElement(medic);
                }

                medicComboBox.setModel(model);
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }


    private void addProgramare() {
        int idReceptioner = Integer.parseInt(receptionerField.getText());
        String numePacient = numePacientField.getText();
        String prenumePacient = prenumePacientField.getText();

        // Get the selected Medic from the JComboBox
        Medic selectedMedic = (Medic) medicComboBox.getSelectedItem();
        int idMedic = selectedMedic.getIdAngajat();

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
        new HomePage(idReceptioner).setVisible(true);
    }

    private void goBack() {
        dispose();
        new HomePage(idReceptioner).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int receptionistId = 10;
                new ProgramarePage(new HomePage(receptionistId), receptionistId);
            }
        });
    }
}
