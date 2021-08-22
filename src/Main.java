import Views.Appointments;
import Views.Dates;
import Views.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

//        JFrame frame = new JFrame("Login");
//        frame.setContentPane(new Login().Main);

        JFrame frame = new JFrame("Appointments");
        frame.setContentPane(new Appointments().Main);

//        JFrame frame = new JFrame("Dates");
//        frame.setContentPane(new Dates().Main);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
