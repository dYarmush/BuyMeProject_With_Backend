package utils;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to read write and edit variables for a Database
 */
public class SQLUtilities {
    private static Connection con = DBConnectionSingleton.getInstance();
    public static int test_id = 1;

    public SQLUtilities() {
    }

    /**
     * method to create the initiol config table
     * @throws SQLException in case of SQL Error
     */
    public static void createConfigTable() throws SQLException {
        String statementToExecute = "CREATE TABLE " + Constants.DATABASE_NAME + ".`config`(`config_id` INT NOT NULL," +
                "`config_name` VARCHAR(45) NOT NULL,`config_data` VARCHAR(100) NOT NULL, PRIMARY KEY (`config_id`));";
        con.createStatement().execute(statementToExecute);

    }
    /**
     * method to create the initiol History table
     * @throws SQLException in case of SQL Error
     */
    public static void createHistoryTable() throws SQLException {
        String statementToExecute = "CREATE TABLE " + Constants.DATABASE_NAME + ".`history`(`test_id` INT NOT NULL," +
                "`test_date` DATETIME NOT NULL,`test_status` VARCHAR(100) NOT NULL, PRIMARY KEY (`test_id`));";
        con.createStatement().execute(statementToExecute);

    }
    /**
     * method to insert the initiol config table data using PreparedStatement
     * @throws SQLException in case of SQL Error
     */
    public static void insertRowData() throws SQLException {
        int config_id = 1;
        String config_name = "URL";
        String config_data = "https://www.buyme.co.il";
        String stmt = "INSERT INTO " + Constants.DATABASE_NAME + ".config" + " (config_id, config_name, config_data) VALUES (?,?,?)";
        PreparedStatement prepStmt = con.prepareStatement(stmt);
        prepStmt.setInt(1, config_id);
        prepStmt.setString(2, config_name);
        prepStmt.setString(3, config_data);
        prepStmt.executeUpdate();
        //inserts 2nc row
        config_id = 2;
        config_name = "BROWSER";
        config_data = "chrome";
        prepStmt.setInt(1, config_id);
        prepStmt.setString(2, config_name);
        prepStmt.setString(3, config_data);
        prepStmt.executeUpdate();
    }

    /**
     * Method that reads the URL from the config database
     * @return the URL as a string
     * @throws SQLException in case of SQL error
     */
    public static String readURLFromDB() throws SQLException {
        String statementToExecute = "SELECT `config_data` FROM " + Constants.DATABASE_NAME + ".config WHERE `config_name` = 'URL' ;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        rs.next();
        return rs.getString("config_data");
    }
    /**
     * Method that reads the Browser type from the config database
     * @return the Browser type as a string
     * @throws SQLException in case of SQL error
     */
    public static String readBrowserFromDB() throws SQLException {
        String statement = "SELECT `config_data` FROM " + Constants.DATABASE_NAME + ".config WHERE `config_name` = 'BROWSER' ;";
        String stmt = "SELECT ? FROM " + Constants.DATABASE_NAME + ".config WHERE `config_name` = ? ;";

        PreparedStatement preparedStatement = con.prepareStatement(statement);
        preparedStatement.setString(1,"config_data");
        preparedStatement.setString(2,"browser");

        ResultSet rs = preparedStatement.executeQuery();

        rs.next();
        return rs.getString("config_data");
    }

    /**
     * Method that writes the test results to a database
     * @param con-databse connections
     * @param status status of test
     * @throws SQLException in case of SQL error
     */
    public static void writeTestResultsToDB(Connection con, String status) throws SQLException {
        test_id=getMaxId()+1;

        String statementToExecute = "INSERT INTO " + Constants.DATABASE_NAME + ".history (`test_id`, `test_date`, `test_status`) " +
                "VALUES ('" + test_id + "', '" + getDate() + "','" + status + "');";
        con.createStatement().execute(statementToExecute);
    }
    /**
     * Gets the current dateTime
     * @returns Date object currentTime
     */
    public static String  getDate(){
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        return currentTime;
    }

    /**
     * Method to read from a table
     * @param con databse connection
     * @throws SQLException in case of SQL error
     */
    public static void getTableContent(Connection con) throws SQLException {
        String statementToExecute = "SELECT * FROM " + Constants.DATABASE_NAME + ".history;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while (rs.next()) {
            //Retrieve by column name
            int id = rs.getInt("test_id");
            String date = rs.getString("test_date");
            String status = rs.getString("test_status");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", date: " + date);
            System.out.print(", status: " + status);
            System.out.println("");
        }
    }
    /**
     * Method that gets the Max test Id value from a database
     * @return the Max test Id value
     * @throws SQLException in case of SQL error
     */
    public static int getMaxId() throws SQLException{
        int id=0;
        String statementToExecute = "SELECT MAX(test_id) FROM " + Constants.DATABASE_NAME + ".history;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while (rs.next()) {
             id = rs.getInt("MAX(test_id)");
        }
        return id;
    }
}