package utils;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utils.Constants.SERVER;

/**
 * Class that creates and checks for instance of single database connection
 */
public class DBConnectionSingleton {
    private static Connection con;


    public DBConnectionSingleton(){}

    /**
     * Method that checks that a Connection is null and if so instantiates the Connection
     *
     * @return a Connection
     */
    public static Connection getInstance(){
        if (con == null) {
            try {
                con =  DriverManager.getConnection("jdbc:mysql://"+Constants.SERVER+":"+Constants.PORT,
                        Constants.USER_NAME, Constants.PASSWORD);
            }catch (SQLException s){s.printStackTrace();}
        }
        return con;
    }

}
