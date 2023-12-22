package com.registrationform.db;

import com.registrationform.userdao.User;

import java.sql.*;

public class DataBaseConnection {
    private static final String url="jdbc:mysql://localhost:3306/users";
    private static final String user = "root";
    private static final String db_password = "MnogoTrudnaParola_1";

    public static int create(String emailAddr, String firstName, String middleName, String lastName, String password, String userName, String dob){
        return 0;
    }

    public static boolean checkExistMail(String email){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,db_password);
            PreparedStatement ps = connection.prepareStatement("select * from users.users where emailAddr = ?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                connection.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkExistUsername(String username){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,db_password);
            PreparedStatement ps = connection.prepareStatement("select * from users.users where userName = ?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                connection.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String login(String email, String password)  {
    String emailAddr = "-1";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,db_password);
            PreparedStatement ps = connection.prepareStatement("select emailAddr from users.users where emailAddr = ? and password = ?");
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                emailAddr = rs.getString(1);
               connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return emailAddr;
    }

    public static User extract(String email){
        User usr = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,db_password);
            PreparedStatement ps = connection.prepareStatement("select firstName,middleName,lastName,userName,emailVerified from users.users where emailAddr = ?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usr = new User(email,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5));
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usr;
    }

    public static int update(String oldEmail, String email,String firstName,String middle,String lastName,String username){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,db_password);
            PreparedStatement ps = connection.prepareStatement("update users.users set emailAddr = ?,firstName = ?, middleName = ?, lastName = ?, userName = ? where emailAddr = ?");
            ps.setString(1,email);
            ps.setString(2,firstName);
            ps.setString(3,middle);
            ps.setString(4,lastName);
            ps.setString(5,username);
            ps.setString(6,oldEmail);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            //return -1 in case of sqlexception
            return -1;
        } catch (ClassNotFoundException e) {
            //return -2 in case of class not found exception
            return -2;
        }

        return 0;
    }

    public static boolean insertNewUser(User userType,String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,db_password);
            PreparedStatement ps = connection.prepareStatement("insert into users.users (emailAddr, firstName, middleName, lastName, password, userName) "
                                                                + "values (?,?,?,?,?,?)");
            ps.setString(1,userType.getEmail());
            ps.setString(2,userType.getFirstName());
            ps.setString(3,userType.getMiddleName());
            ps.setString(4,userType.getLastName());
            ps.setString(5,password);
            ps.setString(6,userType.getUserName());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            //return -1 in case of sqlexception
            return false;
        } catch (ClassNotFoundException e) {
            //return -2 in case of class not found exception
            return false;
        }
        return true;
    }

    public static boolean activateEmail(String email){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,db_password);
            PreparedStatement ps = connection.prepareStatement("update users.users set emailVerified = 1 where emailAddr = ?");
            ps.setString(1,email);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            //return -1 in case of sqlexception
            return false;
        } catch (ClassNotFoundException e) {
            //return -2 in case of class not found exception
            return false;
        }
        return true;
    }
}
