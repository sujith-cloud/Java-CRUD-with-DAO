package DAO;

import DB.DBUtils;
import DTO.ClientsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsDAO {

    static DBUtils dbUtils = new DBUtils();

    private static final String Q_INSERT_INTO_CLIENT = "insert into client (first_name, last_name, dob, NIC, gender, telephone, email, address) values ( ?, ?, ?, ?, ?, ?, ?, ? )";

    private static final String Q_UPDATE_INTO_CLIENT = "update client set first_name=?, last_name=?, dob=?, NIC=?, gender=?, telephone=?, email=?, address=? where id = ?";

    private static final String Q_SELECT_ALL_CLIENT_BY_ID = "select * from client where id=?";

    private static final String Q_SELECT_ALL_CLIENT = "select * from client";

    private static final String Q_DELETE_CLIENT_BY_ID = "delete from client where id=?";


    public static int insertClient(ClientsDTO clientsDTO) throws Exception {
        int count = 0;
        int id = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try (Connection connection = conn = dbUtils.connect()) {
            pstmt = conn.prepareStatement(Q_INSERT_INTO_CLIENT);

            pstmt.setInt(1, id);
            pstmt.setString(2, clientsDTO.getFirst_name());
            pstmt.setString(3, clientsDTO.getLast_name());
            pstmt.setString(4, clientsDTO.getDob());
            pstmt.setString(5, clientsDTO.getNIC());
            pstmt.setString(6, clientsDTO.getGender());
            pstmt.setString(7, clientsDTO.getTelephone());
            pstmt.setString(8, clientsDTO.getEmail());
            pstmt.setString(9, clientsDTO.getAddress());

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

    public static ClientsDTO searchClientById(ClientsDTO id) throws Exception {

        ClientsDTO clientsDTO = new ClientsDTO();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();

        pstmt = conn.prepareStatement(Q_SELECT_ALL_CLIENT_BY_ID);
        pstmt.setString(1, String.valueOf(id));
        rs = pstmt.executeQuery();

        if (rs.next()) {
            clientsDTO.setFirst_name(rs.getString("first_name"));
            clientsDTO.setLast_name(rs.getString("last_name"));
            clientsDTO.setDob(rs.getString("dob"));
            clientsDTO.setNIC(rs.getString("NIC"));
            clientsDTO.setGender(rs.getString("gender"));
            clientsDTO.setTelephone(rs.getString("telephone"));
            clientsDTO.setEmail(rs.getString("email"));
            clientsDTO.setAddress(rs.getString("address"));
        }
        conn.close();

        return clientsDTO;

    }

    public static List<ClientsDTO> getAllClients() throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();
        List<ClientsDTO> clientsDTOList = new ArrayList<>();
        pstmt = conn.prepareStatement(Q_SELECT_ALL_CLIENT);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            ClientsDTO clientsDTO = new ClientsDTO();
            clientsDTO.setFirst_name(rs.getString("first_name"));
            clientsDTO.setLast_name(rs.getString("last_name"));
            clientsDTO.setDob(rs.getString("dob"));
            clientsDTO.setNIC(rs.getString("NIC"));
            clientsDTO.setGender(rs.getString("gender"));
            clientsDTO.setTelephone(rs.getString("telephone"));
            clientsDTO.setEmail(rs.getString("email"));
            clientsDTO.setAddress(rs.getString("address"));
            clientsDTOList.add(clientsDTO);
        }
        conn.close();

        return clientsDTOList;

    }


    public static int updateClient(ClientsDTO clientsDTO) throws Exception {
        int count = 0;
        boolean comma = false;

        String T_update = "update client set ";


        T_update.concat(" where id = ?");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        conn = dbUtils.connect();
        pstmt = conn.prepareStatement(Q_UPDATE_INTO_CLIENT);

        pstmt.setString(1, clientsDTO.getFirst_name());
        pstmt.setString(2, clientsDTO.getLast_name());
        pstmt.setString(3, clientsDTO.getDob());
        pstmt.setString(4, clientsDTO.getNIC());
        pstmt.setString(5, clientsDTO.getGender());
        pstmt.setString(6, clientsDTO.getTelephone());
        pstmt.setString(7, clientsDTO.getEmail());
        pstmt.setString(8, clientsDTO.getAddress());
        pstmt.setString(9, clientsDTO.getId());

        count = pstmt.executeUpdate();
        conn.close();
        return count;

    }

    public static int deleteClientById(ClientsDTO id) throws Exception {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = dbUtils.connect();

        pstmt = conn.prepareStatement(Q_DELETE_CLIENT_BY_ID);
        pstmt.setString(1, String.valueOf(id));
        int i = pstmt.executeUpdate();

        conn.close();

        return i;

    }
}
