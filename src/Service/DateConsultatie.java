package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Service.ConsultatieService.adaugaConsultatie;

public class DateConsultatie extends JFrame {
    private JPanel contentPane;
    private JButton submitButton;
    private JButton backButton; // Added Back button
    private JTextField idAsistentTextField;
    private JTextField idPacientTextField;
    private JTextField cnpPacientTextField;
    private JTextField idProgramareTextField;
    private JTextField simptomeTextField;
    private JTextField investigatiiTextField;
    private JTextField diagnosticTextField;
    private JTextField recomandariTextField;
    private JTextField parafaTextField;

    private int id;

    public DateConsultatie(JFrame parent, int id) {
        this.id = id;

        setTitle("Consultation Form Page");
        setSize(600, 475);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idAsistentTextField = new JTextField(String.valueOf(id));
        idPacientTextField = new JTextField(10);
        cnpPacientTextField = new JTextField(13);
        idProgramareTextField = new JTextField(10);
        simptomeTextField = new JTextField(10);
        investigatiiTextField = new JTextField(10);
        diagnosticTextField = new JTextField(10);
        recomandariTextField = new JTextField(10);
        parafaTextField = new JTextField(10);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleConsultationSubmission();
            }
        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        addComponent(contentPane, new JLabel("ID Asistent:"), gbc, 0, 0);
        addComponent(contentPane, idAsistentTextField, gbc, 1, 0);
        addComponent(contentPane, new JLabel("ID Pacient:"), gbc, 0, 2);
        addComponent(contentPane, idPacientTextField, gbc, 1, 2);
        addComponent(contentPane, new JLabel("CNP Pacient:"), gbc, 0, 3);
        addComponent(contentPane, cnpPacientTextField, gbc, 1, 3);
        addComponent(contentPane, new JLabel("ID Programare:"), gbc, 0, 4);
        addComponent(contentPane, idProgramareTextField, gbc, 1, 4);
        addComponent(contentPane, new JLabel("Simptome:"), gbc, 0, 6);
        addComponent(contentPane, simptomeTextField, gbc, 1, 6);
        addComponent(contentPane, new JLabel("Investigatii:"), gbc, 0, 7);
        addComponent(contentPane, investigatiiTextField, gbc, 1, 7);
        addComponent(contentPane, new JLabel("Diagnostic:"), gbc, 0, 8);
        addComponent(contentPane, diagnosticTextField, gbc, 1, 8);
        addComponent(contentPane, new JLabel("Recomandari:"), gbc, 0, 9);
        addComponent(contentPane, recomandariTextField, gbc, 1, 9);
        addComponent(contentPane, new JLabel("Parafa:"), gbc, 0, 10);
        addComponent(contentPane, parafaTextField, gbc, 1, 10);
        addComponent(contentPane, submitButton, gbc, 0, 11, 2, 1);
        addComponent(contentPane, backButton, gbc, 0, 12, 2, 1); // Added Back button

        add(contentPane);

        setLocationRelativeTo(null);
    }

    private void addComponent(Container container, Component component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        container.add(component, gbc);
    }

    private void addComponent(Container container, Component component, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        container.add(component, gbc);
    }

    private void handleConsultationSubmission() {
        try {
            Integer idAsistent = Integer.valueOf(idAsistentTextField.getText());
            String cnpPacient = cnpPacientTextField.getText();
            Integer idPacient = Integer.valueOf(idPacientTextField.getText());
            Integer idProgramare = Integer.valueOf(idProgramareTextField.getText());
            String simptome = simptomeTextField.getText();
            String investigatii = investigatiiTextField.getText();
            String diagnostic = diagnosticTextField.getText();
            String recomandari = recomandariTextField.getText();
            String parafa = parafaTextField.getText();

            adaugaConsultatie(idPacient, cnpPacient, idAsistent, idProgramare, simptome, investigatii, diagnostic, recomandari, parafa);

            ConsultatieService.adaugaBonForConsultatie(idProgramare);

            JOptionPane.showMessageDialog(this, "Consultation and Bon added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new HomePage(id).setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid numeric input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBack() {
        dispose();
        new HomePage(id).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DateConsultatie(null, 123).setVisible(true);
            }
        });
    }
}
