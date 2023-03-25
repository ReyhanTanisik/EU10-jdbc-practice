package jdbcTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@54.152.217.128:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //move to first row
        //resultSet.next();
        //System.out.println(resultSet.getString(2));


        // display department table in 10 - administration-200-1700 format

        // code for iterating  for each row

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getString(3) + " - " + resultSet.getInt(4));
        }


        resultSet = statement.executeQuery("Select * from regions");

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        }


        //close connection

        resultSet.close();
        statement.close();
        connection.close();


    }

    @Test
    public void Test2() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //move to last row
        resultSet.last();

        // get the row count

        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        // to move before first row after we use last method
        resultSet.beforeFirst();

        // print all second column information
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }


        //close connection

        resultSet.close();
        statement.close();
        connection.close();


    }


    @Test
    public void Test3() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from departments");


        // get the database related date inside the dbMetadata object
        DatabaseMetaData dbMetadata= connection.getMetaData();
        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        dbMetadata.getDriverVersion();


       //get the resultsetmetadata
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        // how many columns we have

        int colCount=rsMetaData.getColumnCount();
        System.out.println(colCount);

        // getting column names
        System.out.println(rsMetaData.getColumnName(1));
        System.out.println(rsMetaData.getColumnName(2));


        //print all the column names dynamically
        for (int i = 1; i <= colCount; i++) {

            System.out.println(rsMetaData.getColumnName(i));

        }


        //close connection

        resultSet.close();
        statement.close();
        connection.close();

    }

}