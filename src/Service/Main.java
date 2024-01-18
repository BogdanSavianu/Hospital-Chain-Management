package Service;

import Model.Autentificare;
import Repository.UtilizatoriRepository;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Repository.UtilizatoriRepository.getUser;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
                login.setSize(300, 200);
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            }
        });
    }
}

