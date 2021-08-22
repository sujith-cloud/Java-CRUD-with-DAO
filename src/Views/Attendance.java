package Views;

import DAO.AttendanceDAO;
import DB.DBUtils;
import DTO.AttendanceDTO;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Attendance {
    private JPanel Main;
    private JButton saveButton;
    private JScrollPane table_1;
    private JTable table1;
    private JButton updateButton;
    private JTextField txtId;
    private JButton searchButton;
    private JButton deleteButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    AttendanceDTO attendanceDTO = new AttendanceDTO();
    DBUtils dbUtils = new DBUtils();

    public Attendance() {

        dbUtils.connect();
        table_load();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String appointmentdate,appointmenttime,appointmenttitle;

//                appointmentdate = spdate.getValue().toString();
//                appointmenttime = sptime.getValue().toString();
//                appointmenttitle = txttitle.getText();

//                appointmentDTO.setDate(appointmentdate);
//                appointmentDTO.setTime(appointmenttime);
//                appointmentDTO.setTitle(appointmenttitle);


                try
                {
                    AttendanceDAO.insertAttendance(attendanceDTO);
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

                attendanceDTO.setId(appointmentid);

                if(!appointmentid.equals(""))
                {

                    try
                    {
                        AttendanceDAO.updateAttendance(attendanceDTO);
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

                attendanceDTO.setId(appointmentid);

                if(!appointmentid.equals(""))
                {

                    try
                    {
                        AttendanceDAO.deleteAttendanceById(attendanceDTO);
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
                            AttendanceDAO.searchAttendanceById(attendanceDTO);

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

    }

    void table_load()
    {
        try
        {
            for (AttendanceDTO allAttendance : AttendanceDAO.getAllAttendances()) {
                table1.setModel((TableModel) allAttendance);
            }

        }
        catch (SQLException e)

        {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
