package DAO;

import DB.DBUtils;
import DTO.AppointmentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    static DBUtils dbUtils = new DBUtils();

    private static final String Q_INSERT_INTO_APPOINTMENT = "insert into appointment (date, time, title) values ( ?, ?, ? )";

    private static final String Q_UPDATE_INTO_APPOINTMENT = "update appointment set date=?, time=?, title=? where id = ?";

    private static final String Q_SELECT_ALL_APPOINTMENT_BY_ID = "select * from appointment where id=?";

    private static final String Q_SELECT_ALL_APPOINTMENT = "select * from appointment";

    private static final String Q_DELETE_APPOINTMENT_BY_ID = "delete from appointment where id=?";


    public static int insertAppointment(AppointmentDTO appointmentDTO) throws Exception {
        int count = 0;
        int id = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try (Connection connection = conn = dbUtils.connect()) {
            pstmt = conn.prepareStatement(Q_INSERT_INTO_APPOINTMENT);

            pstmt.setInt(1, id);
//            pstmt.setString(2, appointmentDTO.getDate());
//            pstmt.setString(3, appointmentDTO.getTime());
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

    public static AppointmentDTO searchAppointmentById(int id) throws Exception {

        AppointmentDTO appointmentDTO = new AppointmentDTO();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();

        pstmt = conn.prepareStatement(Q_SELECT_ALL_APPOINTMENT_BY_ID);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();

        if (rs.next()) {
//            appointmentDTO.setDate(rs.getString("date"));
//            appointmentDTO.setTime(rs.getString("time"));
            appointmentDTO.setTitle(rs.getString("title"));
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
//            appointmentDTO.setDate(rs.getString("date"));;
//            appointmentDTO.setTime(rs.getString("time"));
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

        //update using different logics

//        if (appointmentDTO.getDate() != null) {
//            T_update.concat(" date = ?");
//            T_update.concat(", ");
//            comma = true;
//        }

//        if (appointmentDTO.getTime() != null) {
//            T_update.concat(" time = ? ");
//            if (!comma) {
//                T_update.concat(", ");
//                comma = true;
//            }
//        }
        if (appointmentDTO.getTitle() != null) {
            T_update.concat(" title = ? ");
            if (!comma) {
                T_update.concat(", ");
                comma = true;
            }
        }
        T_update.concat(" where id = ?");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn = dbUtils.connect();
        pstmt = conn.prepareStatement(Q_UPDATE_INTO_APPOINTMENT);

//        pstmt.setString(1, appointmentDTO.getDate());
//        pstmt.setString(2, appointmentDTO.getTime());
        pstmt.setString(3, appointmentDTO.getTitle());
        pstmt.setString(4, appointmentDTO.getId());

        count = pstmt.executeUpdate();
        conn.close();
        return count;

    }

    public static int deleteAppointmentById(int id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();

        pstmt = conn.prepareStatement(Q_DELETE_APPOINTMENT_BY_ID);
        pstmt.setInt(1, id);
        int i = pstmt.executeUpdate();

        conn.close();

        return i;

    }
}
