package jdbcTest;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {
        String dbUrl="jdbc:oracle:thin:@54.152.217.128:1521:XE";
        String dbUsername="hr";
        String dbPassword="hr";

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement =connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from regions");


        //once you set up connection default pointer looks for 0
        //1.next() ---> move pointer to first row (row'da ilerletir)


        //resultSet.next();


        //row'u next() ile ilerletebiliriz. column ilerletmek için ise 2 seçenek var, ya column ismi ya da column indexi)
        //2.getting information with column name


       // System.out.println(resultSet.getString("region_name"));

        //Europe
        //2.getting information(2ndWay) with column index(starts 1)


       // System.out.println(resultSet.getString(2));            //Europe
        //you will choose (getString,getInt) this data type according to what you gonna display from database

        //1- europe
        //2-americas

        //System.out.println(resultSet.getInt(1) + " -  " + resultSet.getString(2));
        //resultSet.next();

       // System.out.println(resultSet.getInt(1) + " -  " + resultSet.getString(2));

        while(resultSet.next()){

            System.out.println(resultSet.getInt(1) + " -  " + resultSet.getString(2));
        }
        System.out.println("-----------------");
     resultSet.close();
     statement.close();
     connection.close();

    }
}
