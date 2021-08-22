package DAO;

import DB.DBUtils;
import DTO.AppointmentDTO;
import DTO.DatesDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatesDAO {

    static DBUtils dbUtils = new DBUtils();

    private static final String Q_INSERT_INTO_DATES = "insert into dates (available_date, available_time, date_availability) values ( ?, ?, ? )";

    private static final String Q_UPDATE_INTO_DATES = "update dates set available_date=?, available_time=?, date_availability=? where id = ?";

    private static final String Q_SELECT_ALL_DATES_BY_ID = "select * from dates where id=?";

    private static final String Q_SELECT_ALL_DATES = "select * from dates";

    private static final String Q_DELETE_DATES_BY_ID = "delete from dates where id=?";


    public static int insertDates(DatesDTO datesDTO) throws Exception {
        int count = 0;
        int id = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try (Connection connection = conn = dbUtils.connect()) {
            pstmt = conn.prepareStatement(Q_INSERT_INTO_DATES);

            pstmt.setInt(1, id);
            pstmt.setString(2, datesDTO.getAvailable_date());
            pstmt.setString(3, datesDTO.getAvailable_time());
            pstmt.setString(3, datesDTO.getDate_availability());

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

    public static DatesDTO searchDatesById(DatesDTO id) throws Exception {

        DatesDTO datesDTO = new DatesDTO();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();

        pstmt = conn.prepareStatement(Q_SELECT_ALL_DATES_BY_ID);
        pstmt.setString(1, String.valueOf(id));
        rs = pstmt.executeQuery();

        if (rs.next()) {
            datesDTO.setAvailable_date(rs.getString("available_date"));
            datesDTO.setAvailable_time(rs.getString("available_time"));
            datesDTO.setDate_availability(rs.getString("date_availability"));
        }
        conn.close();

        return datesDTO;

    }

    public static List<DatesDTO> getAllDates() throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();
        List<DatesDTO> datesDTOList = new ArrayList<>();
        pstmt = conn.prepareStatement(Q_SELECT_ALL_DATES);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            DatesDTO datesDTO = new DatesDTO();
            datesDTO.setAvailable_date(rs.getString("available_date"));
            datesDTO.setAvailable_time(rs.getString("available_time"));
            datesDTO.setDate_availability(rs.getString("date_availability"));
            datesDTOList.add(datesDTO);
        }
        conn.close();

        return datesDTOList;

    }


    public static int updateDates(DatesDTO datesDTO) throws Exception {
        int count = 0;
        boolean comma = false;

        String T_update = "update dates set ";


        T_update.concat(" where id = ?");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn = dbUtils.connect();
        pstmt = conn.prepareStatement(Q_UPDATE_INTO_DATES);

        pstmt.setString(1, datesDTO.getAvailable_date());
        pstmt.setString(2, datesDTO.getAvailable_time());
        pstmt.setString(3, datesDTO.getDate_availability());
        pstmt.setString(4, datesDTO.getId());

        count = pstmt.executeUpdate();
        conn.close();
        return count;

    }

    public static int deleteDatesById(DatesDTO id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();

        pstmt = conn.prepareStatement(Q_DELETE_DATES_BY_ID);
        pstmt.setString(1, String.valueOf(id));
        int i = pstmt.executeUpdate();

        conn.close();

        return i;

    }
}
