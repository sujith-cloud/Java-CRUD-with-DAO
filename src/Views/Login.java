package Views;

import DB.DBUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    public JPanel Main;
    private JTextField txtUsername;
    private JButton btnLogin;
    private JComboBox cmbUserRoll;
    private JTextField txtPassword;

    DBUtils dbUtils = new DBUtils();

    PreparedStatement pst;
    ResultSet rs;

    public Login() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {

                    String userRoll, Username, Password;

                    userRoll = String.valueOf(cmbUserRoll.getSelectedItem());
                    Username = txtUsername.getText();
                    Password = txtPassword.getText();


                    pst = dbUtils.con.prepareStatement("select user_roll, username, password from login where user_roll = ?, username = ? , password = ? ");

                    pst.setString(1, userRoll);
                    pst.setString(2, Username);
                    pst.setString(3, Password);
                    rs = pst.executeQuery();
                    if (rs.next() == true) {

                        JOptionPane.showMessageDialog(null, "You are logged in");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Login");
                    }


//                    else
//                    {
//                        JOptionPane.showMessageDialog(null, "Please fill the blanks");
//                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
