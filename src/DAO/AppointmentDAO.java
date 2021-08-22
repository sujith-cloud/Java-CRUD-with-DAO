package DAO;

import DB.DBUtils;
import DTO.AppointmentDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    static DBUtils dbUtils = new DBUtils();

    private static final String Q_INSERT_INTO_APPOINTMENT = "insert into appointment (appointment_date, appointment_time, title) values ( ?, ?, ? )";

    private static final String Q_UPDATE_INTO_APPOINTMENT = "update appointment set appointment_date=?, appointment_time=?, title=? where id = ?";

    private static final String Q_SELECT_ALL_APPOINTMENT_BY_ID = "select * from appointment where id=?";

    private static final String Q_SELECT_ALL_APPOINTMENT = "select * from appointment";

    private static final String Q_DELETE_APPOINTMENT_BY_ID = "delete from appointment where id=?";


    public static int insertAppointment(AppointmentDTO appointmentDTO) throws Exception {
        int count = 0;
        int id = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = dbUtils.connect();
            pstmt = conn.prepareStatement(Q_INSERT_INTO_APPOINTMENT);

            pstmt.setString(1, String.valueOf(id));
            pstmt.setString(2, appointmentDTO.getAvailable_date());
            pstmt.setString(3, appointmentDTO.getAvailable_time());
            pstmt.setString(4, appointmentDTO.getTitle());

            id++;

            count = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }


        return count;
    }

    public static AppointmentDTO searchAppointmentById(AppointmentDTO id) throws Exception {

        AppointmentDTO appointmentDTO = new AppointmentDTO();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();

        pstmt = conn.prepareStatement(Q_SELECT_ALL_APPOINTMENT_BY_ID);
        pstmt.setString(1, String.valueOf(id));
        rs = pstmt.executeQuery();

        if (rs.next()==true) {
            appointmentDTO.setAvailable_date(rs.getString("appointment_date"));
            appointmentDTO.setAvailable_time(rs.getString("appointment_time"));
            appointmentDTO.setTitle(rs.getString("title"));
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Invalid ID");
        }
        conn.close();

        return appointmentDTO;

    }

    public static List<AppointmentDTO> getAllAppointments() throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();
        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();
        pstmt = conn.prepareStatement(Q_SELECT_ALL_APPOINTMENT);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setAvailable_date(rs.getString("appointment_date"));;
            appointmentDTO.setAvailable_time(rs.getString("appointment_time"));
            appointmentDTO.setTitle(rs.getString("title"));
            appointmentDTOList.add(appointmentDTO);
        }
        conn.close();

        return appointmentDTOList;

    }


    public static int updateAppointment(AppointmentDTO appointmentDTO) throws Exception {
        int count = 0;
        boolean comma = false;

        String T_update = "update appointment set ";

        T_update.concat(" where id = ?");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn = dbUtils.connect();
        pstmt = conn.prepareStatement(Q_UPDATE_INTO_APPOINTMENT);

        pstmt.setString(1, appointmentDTO.getAvailable_date());
        pstmt.setString(2, appointmentDTO.getAvailable_time());
        pstmt.setString(3, appointmentDTO.getTitle());
        pstmt.setString(4, appointmentDTO.getId());

        count = pstmt.executeUpdate();
        conn.close();
        return count;

    }

    public static int deleteAppointmentById(AppointmentDTO id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();

        pstmt = conn.prepareStatement(Q_DELETE_APPOINTMENT_BY_ID);
        pstmt.setString(1, String.valueOf(id));
        int i = pstmt.executeUpdate();

        conn.close();

        return i;

    }
}
