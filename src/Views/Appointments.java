package Views;

import DAO.AppointmentDAO;
import DB.DBUtils;
import DTO.AppointmentDTO;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Appointments {
    public JPanel Main;
    private JTextField txttitle;
    private JButton saveButton;
    private JScrollPane table_1;
    private JTable table1;
    private JButton updateButton;
    private JTextField txtId;
    private JButton searchButton;
    private JButton deleteButton;
    private JSpinner spdate;
    private JSpinner sptime;

    AppointmentDTO appointmentDTO = new AppointmentDTO();
    DBUtils dbUtils = new DBUtils();
    PreparedStatement pst;

    public Appointments() {

        dbUtils.connect();
        table_load();


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String appointmentdate,appointmenttime,appointmenttitle;

                appointmentdate = spdate.getValue().toString();
                appointmenttime = sptime.getValue().toString();
                appointmenttitle = txttitle.getText();

                appointmentDTO.setAvailable_date(appointmentdate);
                appointmentDTO.setAvailable_time(appointmenttime);
                appointmentDTO.setTitle(appointmenttitle);


                try
                {
                    AppointmentDAO.insertAppointment(appointmentDTO);
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }

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
                            AppointmentDAO.searchAppointmentById(appointmentDTO);

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
                appointmentdate = spdate.getValue().toString();
                appointmenttime = sptime.getValue().toString();

                appointmentDTO.setId(appointmentid);

                if(!appointmentid.equals(""))
                {

                    try
                    {
                        AppointmentDAO.updateAppointment(appointmentDTO);
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

                appointmentDTO.setId(appointmentid);

                if(!appointmentid.equals(""))
                {

                    try
                    {
                        AppointmentDAO.deleteAppointmentById(appointmentDTO);
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

            pst = dbUtils.con.prepareStatement("select * from appointment");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));

//            for (AppointmentDTO allAppointment : AppointmentDAO.getAllAppointments()) {
//                table1.setModel((TableModel) allAppointment);
//            }

        }
        catch (SQLException e)

        {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
