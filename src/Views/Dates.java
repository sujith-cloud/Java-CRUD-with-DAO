package Views;

import DAO.DatesDAO;
import DB.DBUtils;
import DTO.DatesDTO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;


public class Dates {
    public JPanel Main;
    private JButton saveButton;
    private JScrollPane table_1;
    private JTable table1;
    private JButton updateButton;
    private JTextField txtId;
    private JButton searchButton;
    private JButton deleteButton;
    private JSpinner spinner2;
    private JPanel jpDate;
    private JPanel jpTime;
    private JSpinner spinner1;
    private JTextField txtDate;
    private JPanel jpCald;

    Calendar cld = Calendar.getInstance();
    JDateChooser dateChooser = new JDateChooser(cld.getTime());
    JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());

    DatesDTO datesDTO = new DatesDTO();
    DBUtils dbUtils = new DBUtils();

    public Dates() {

        dbUtils.connect();
        table_load();

        dateChooser.setDateFormatString("dd/MM/yyyy");
        jpDate.add(dateChooser);


        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        spinner2.setEditor(timeEditor);
        spinner2.setValue(new Date());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String ddate,dtime;

//                ddate = spdate.getValue().toString();
//                dtime = sptime.getValue().toString();

//                datesDTO.setDate(ddate);
//                datesDTO.setTime(dtime);


                try
                {
                    DatesDAO.insertDates(datesDTO);
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
                            DatesDAO.searchDatesById(datesDTO);

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

                String did,ddate,dtime;

                did = txtId.getText();
//                ddate = spdate.getValue().toString();
//                dtime = sptime.getValue().toString();

                datesDTO.setId(did);

                if(!did.equals(""))
                {

                    try
                    {
                        DatesDAO.updateDates(datesDTO);
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

                datesDTO.setId(appointmentid);

                if(!appointmentid.equals(""))
                {

                    try
                    {
                        DatesDAO.deleteDatesById(datesDTO);
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
//        try
//        {
//            for (DatesDAO allDates : DatesDAO.getAllDates()) {
//                table1.setModel((TableModel) allDates);
//            }

//        }
//        catch (SQLException e)

//        {

//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }
}
