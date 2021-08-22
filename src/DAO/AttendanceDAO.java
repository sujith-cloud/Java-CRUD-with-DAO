package DAO;

import DB.DBUtils;
import DTO.AttendanceDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    static DBUtils dbUtils = new DBUtils();

    private static final String Q_INSERT_INTO_ATTENDANCE = "insert into attendance (appointment, status, attendance_date) values ( ?, ?, ? )";

    private static final String Q_UPDATE_INTO_ATTENDANCE = "update attendance set appointment=?, status=?, attendance_date=? where id = ?";

    private static final String Q_SELECT_ALL_ATTENDANCE_BY_ID = "select * from attendance where id=?";

    private static final String Q_SELECT_ALL_ATTENDANCE = "select * from attendance";

    private static final String Q_DELETE_ATTENDANCE_BY_ID = "delete from attendance where id=?";


    public static int insertAttendance(AttendanceDTO attendanceDTO) throws Exception {
        int count = 0;
        int id = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try (Connection connection = conn = dbUtils.connect()) {
            pstmt = conn.prepareStatement(Q_INSERT_INTO_ATTENDANCE);

            pstmt.setInt(1, id);
            pstmt.setString(2, attendanceDTO.getAppointment());
            pstmt.setString(3, attendanceDTO.getStatus());
            pstmt.setString(4, attendanceDTO.getAttendance_date());

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

    public static AttendanceDTO searchAttendanceById(AttendanceDTO id) throws Exception {

        AttendanceDTO attendanceDTO = new AttendanceDTO();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();

        pstmt = conn.prepareStatement(Q_SELECT_ALL_ATTENDANCE_BY_ID);
        pstmt.setString(1, String.valueOf(id));
        rs = pstmt.executeQuery();

        if (rs.next()) {
            attendanceDTO.setAppointment(rs.getString("appointment"));
            attendanceDTO.setStatus(rs.getString("status"));
            attendanceDTO.setAttendance_date(rs.getString("attendance_date"));
        }
        conn.close();

        return attendanceDTO;

    }

    public static List<AttendanceDTO> getAllAttendances() throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();
        List<AttendanceDTO> attendanceDTOList = new ArrayList<>();
        pstmt = conn.prepareStatement(Q_SELECT_ALL_ATTENDANCE);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            attendanceDTO.setAppointment(rs.getString("appointment"));
            attendanceDTO.setStatus(rs.getString("status"));
            attendanceDTO.setAttendance_date(rs.getString("attendance_date"));
            attendanceDTOList.add(attendanceDTO);
        }
        conn.close();

        return attendanceDTOList;

    }


    public static int updateAttendance(AttendanceDTO attendanceDTO) throws Exception {
        int count = 0;
        boolean comma = false;

        String T_update = "update attendance set ";


        T_update.concat(" where id = ?");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn = dbUtils.connect();
        pstmt = conn.prepareStatement(Q_UPDATE_INTO_ATTENDANCE);

        pstmt.setString(1, attendanceDTO.getAppointment());
        pstmt.setString(2, attendanceDTO.getStatus());
        pstmt.setString(3, attendanceDTO.getAttendance_date());
        pstmt.setString(4, attendanceDTO.getId());

        count = pstmt.executeUpdate();
        conn.close();
        return count;

    }

    public static int deleteAttendanceById(AttendanceDTO id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();

        pstmt = conn.prepareStatement(Q_DELETE_ATTENDANCE_BY_ID);
        pstmt.setString(1, String.valueOf(id));
        int i = pstmt.executeUpdate();

        conn.close();

        return i;

    }
}
