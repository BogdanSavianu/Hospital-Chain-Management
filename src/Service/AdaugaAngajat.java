package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdaugaAngajat extends JFrame {
    private JPanel contentPane;
    private JButton inspectorButton;
    private JButton expertButton;
    private JButton receptionerButton;
    private JButton asistentButton;
    private JButton medicButton;
    private JButton backButton;

    private int id;

    public AdaugaAngajat(JFrame parent, Integer id) {
        this.id = id;
        setTitle("Adauga Angajat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(parent);

        contentPane = new JPanel(new GridLayout(6, 1, 10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inspectorButton = new JButton("Inspector Resurse Umane");
        inspectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAngajat("Inspector Resurse Umane");
            }
        });

        expertButton = new JButton("Expert Financiar Contabil");
        expertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAngajat("Expert Financiar Contabil");
            }
        });

        receptionerButton = new JButton("Receptioner");
        receptionerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAngajat("Receptioner");
            }
        });

        asistentButton = new JButton("Asistent Medical");
        asistentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAngajat("Asistent Medical");
            }
        });

        medicButton = new JButton("Medic");
        medicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAngajat("Medic");
            }
        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });

        contentPane.add(inspectorButton);
        contentPane.add(expertButton);
        contentPane.add(receptionerButton);
        contentPane.add(asistentButton);
        contentPane.add(medicButton);
        contentPane.add(backButton);

        add(contentPane);
        setVisible(true);
    }

    private void addAngajat(String angajatType) {
        if ("Medic".equals(angajatType)) {
            dispose();
            AdaugaMedic adaugaMedic = new AdaugaMedic(this, id);
            adaugaMedic.setVisible(true);

        } else if ("Asistent Medical".equals(angajatType)) {
            dispose();
            AdaugaAsistentMedical adaugaAsistentMedical = new AdaugaAsistentMedical(this, id);
            adaugaAsistentMedical.setVisible(true);
        } else {
            dispose();
            AdaugaRestulAngajatilor adaugaRestulAngajatilor = new AdaugaRestulAngajatilor(this, angajatType, id);
            adaugaRestulAngajatilor.setVisible(true);
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
                new AdaugaAngajat(null, 1).setVisible(true);
            }
        });
    }
}
