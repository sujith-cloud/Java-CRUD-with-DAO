package Views;

import DAO.ClientsDAO;
import DB.DBUtils;
import DTO.ClientsDTO;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Clients {
    private JPanel Main;
    private JTextField txtFName;
    private JTextField txtSalary;
    private JButton saveButton;
    private JScrollPane table_1;
    private JTable table1;
    private JButton updateButton;
    private JTextField txtId;
    private JButton searchButton;
    private JButton deleteButton;
    private JTextField txtLLName;
    private JComboBox cmbGender;
    private JTextField txtTelephone;
    private JTextField txtemail;
    private JTextArea txtAddress;

    ClientsDTO clientsDTO = new ClientsDTO();
    DBUtils dbUtils = new DBUtils();

    public Clients() {

        dbUtils.connect();
        table_load();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String clientFirstName,clientLastName,clientBirthDate, clientGender, clientTelephone,clientEmail;

                txtFName.getText().toString();
                txtLLName.getText().toString();
              //  cmbGender.().toString();
                txtTelephone.getText().toString();
                txtemail.getText().toString();



//                appointmentdate = spdate.getValue().toString();
//                appointmenttime = sptime.getValue().toString();
//                appointmenttitle = txttitle.getText();

//                appointmentDTO.setDate(appointmentdate);
//                appointmentDTO.setTime(appointmenttime);
//                appointmentDTO.setTitle(appointmenttitle);


                try
                {
                    ClientsDAO.insertClient(clientsDTO);
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try
                {
                    String appointmentid = txtId.getText();

                    if(!appointmentid.equals(""))
                    {

                        try
                        {
                            ClientsDAO.searchClientById(clientsDTO);

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                    else
                    {
                        txtId.setText("");
                        JOptionPane.showMessageDialog(null,"Please select id first");
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String appointmentid,appointmentdate,appointmenttime,appointmenttitle;

                appointmentid = txtId.getText();
//                appointmentdate = spdate.getValue().toString();
//                appointmenttime = sptime.getValue().toString();

                clientsDTO.setId(appointmentid);

                if(!appointmentid.equals(""))
                {

                    try
                    {
                        ClientsDAO.updateClient(clientsDTO);
                        JOptionPane.showMessageDialog(null,"Record Updated");
                        table_load();
                        txtId.setText("");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Please select id first");
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String appointmentid;

                appointmentid = txtId.getText();

                clientsDTO.setId(appointmentid);

                if(!appointmentid.equals(""))
                {

                    try
                    {
                        ClientsDAO.deleteClientById(clientsDTO);
                        JOptionPane.showMessageDialog(null,"Record Deleted");
                        table_load();
                        txtId.setText("");
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Please enter ID first");
                }

            }
        });
    }

    void table_load()
    {
        try
        {
            for (ClientsDTO allClients : ClientsDAO.getAllClients()) {
                table1.setModel((TableModel) allClients);
            }

        }
        catch (SQLException e)

        {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
