package automation.ui.utils;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static automation.ui.utils.ConfigReader.getPropertiesValue;

public class DBUtils {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static void establishConnections() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    getPropertiesValue("db.url"),
                    getPropertiesValue("db.username"),
                    getPropertiesValue("db.password"));

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Map<String, Object>> runSqlSelectQuery(String sqlQuery) {

        List<Map<String,Object>> dbResultList = new ArrayList<>();
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while(resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for(int col=1; col<= columnCount; col++){
                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                dbResultList.add(rowMap);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbResultList;
    }

    public static int runSqlUpdateQuery(String sqlQuery) {
        int rowsAffected = 0;
        try {
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> getFirstRow(String sqlQuery) {

        List<Map<String, Object>> dbResultList = new ArrayList<>();
        try {
            statement =  connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sqlQuery);

            resultSet.first();

            ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

                Map<String, Object> rowMap = new HashMap<>();

                for (int col = 1; col <= columnCount; col++) {
                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                dbResultList.add(rowMap);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbResultList;
    }

    public static List<Map<String, Object>> getLastRow(String sqlQuery) {

        List<Map<String, Object>> dbResultList = new ArrayList<>();
        try {
            statement =  connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sqlQuery);

            resultSet.last();

            ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            Map<String, Object> rowMap = new HashMap<>();

            for (int col = 1; col <= columnCount; col++) {
                rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
            }
            dbResultList.add(rowMap);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbResultList;
    }

    public static List<Map<String, Object>> getNthRow(String sqlQuery, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Row number must be a positive integer.");
        }
        List<Map<String,Object>> dbResultList = new ArrayList<>();
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            resultSet.absolute(n);

            ResultSetMetaData resultSetMetaData = (ResultSetMetaData) resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

                Map<String, Object> rowMap = new HashMap<>();
                for(int col=1; col<= columnCount; col++){
                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                dbResultList.add(rowMap);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbResultList;
    }

}
