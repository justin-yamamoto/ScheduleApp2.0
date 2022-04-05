package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**This gets type data from database. */
public abstract class DAOType {

    /**Creates a list with all appointment type information from the DB. */
    public static ObservableList<Type> getAllAppointmentTypes() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT  TYPE, START, COUNT(TYPE) AS count FROM APPOINTMENTS";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("count");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }

    /** Get list of types and count if in January. */
    public static ObservableList<Type> getJan() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS JANAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-01-31') AND START >=date('2022-01-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("JANAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }

    /** Get list of types and count if in February. */
    public static ObservableList<Type> getFeb() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS FEBAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-01-28') AND START >=date('2022-02-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("FEBAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }


    /** Get list of types and count if in March. */
    public static ObservableList<Type> getMarch() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS MARCHAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-03-31') AND START >=date('2022-03-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("MARCHAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }

    /** Get list of types and count if in April. */
    public static ObservableList<Type> getApril() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS APRILAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-04-30') AND START >=date('2022-04-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("APRILAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }

    /** Get list of types and count if in May. */
    public static ObservableList<Type> getMay() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS MAYAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-05-31') AND START >=date('2022-05-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("MAYAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }
    /** Get list of types and count if in June. */
    public static ObservableList<Type> getJune() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS JUNEAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-06-30') AND START >=date('2022-06-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("JUNEAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }
    /** Get list of types and count if in July. */
    public static ObservableList<Type> getJuly() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS JULYAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-07-31') AND START >=date('2022-07-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("JULYAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }
    /** Get list of types and count if in August. */
    public static ObservableList<Type> getAug() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS AUGAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-08-31') AND START >=date('2022-08-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("AUGAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }
    /** Get list of types and count if in September. */
    public static ObservableList<Type> getSept() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS SEPTAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-09-30') AND START >=date('2022-09-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("SEPTAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }
    /** Get list of types and count if in October. */
    public static ObservableList<Type> getOct() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS OCTAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-10-31') AND START >=date('2022-10-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("OCTAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }
    /** Get list of types and count if in November. */
    public static ObservableList<Type> getNov() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS NOVAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-11-30') AND START >=date('2022-11-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("NOVAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }
    /** Get list of types and count if in December. */
    public static ObservableList<Type> getDec() {
        ObservableList<Type>typeList = FXCollections.observableArrayList();

        try {
            String sql = "select  TYPE,  START, count(TYPE) AS DECAPTS\n" +
                    "FROM APPOINTMENTS\n" +
                    "WHERE START <= LAST_DAY('2022-12-31') AND START >=date('2022-12-01')\n" +
                    "GROUP BY TYPE";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                String type = rs.getString("Type");
                int count = rs.getInt("DECAPTS");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();


                Type newType = new Type(type,count,start);
                typeList.add(newType);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return typeList;
    }

}
