package DB;

import java.sql.*;

public class DBUtils {


    public Connection con;

    public Connection connect()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/RanjanAndCo_AMS_db", "root","");
            System.out.println("Success");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
