package com.uni.datautils;

import com.uni.exceptions.DatabaseConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection getConnection() {
        try {
            InputStream input = ConnectionUtil.class.getClassLoader().getResourceAsStream("database.properties");
            Properties prop = new Properties();
            prop.load(input);
            String testMode = prop.getProperty("test-mode");

            // If running tests, use H2 in-memory database, otherwise, use Postgres database
            if (testMode.equals("true")) {
                Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");

                return conn;
            } else {
                String url = System.getenv("URL");
                String username = System.getenv("USER");
                String password = System.getenv("PASSWORD");
                //example connection string jdbc:postgresql://localhost:5432/postgres?user=postgres&password=password
                Connection conn = DriverManager.getConnection(url, username, password);
                return conn;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new DatabaseConnectionException();
        }
    }

    public static void populateH2Database(Connection conn) {
        String imRoleSql = "create type im_role as enum ('referee','player','admin')";

        String userTableSql = "create table im_user(\n" +
                "    user_id serial primary key,\n" +
                "    username varchar unique,\n" +
                "    password varchar,\n" +
                "    role im_role,\n" +
                "    height int, -- centimeters\n" +
                "    weight int, -- kilograms\n" +
                "    profile_pic varchar,\n" +
                "    display_biometrics bool\n" +
                ")";

        String insertUserSql = "insert into im_user (username, password, role, height, weight, profile_pic, display_biometrics) values (?, ?, ?::im_role, ?, ?, ?, ?)";

        try {
            PreparedStatement ps1 = conn.prepareStatement(imRoleSql); // Create im_role enum
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(userTableSql); // Create im_user table
            ps2.executeUpdate();

            PreparedStatement ps3 = conn.prepareStatement(insertUserSql); // Create a user
            ps3.setString(1, "testing123");
            ps3.setString(2, "12345");
            ps3.setString(3, "player");
            ps3.setInt(4, 70);
            ps3.setInt(5, 150);
            ps3.setString(6, null);
            ps3.setBoolean(7, true);
            ps3.executeUpdate();

            PreparedStatement ps4 = conn.prepareStatement(insertUserSql); // Create a user
            ps4.setString(1, "gatorFan99");
            ps4.setString(2, "testpassword");
            ps4.setString(3, "admin");
            ps4.setInt(4, 64);
            ps4.setInt(5, 135);
            ps4.setString(6, null);
            ps4.setBoolean(7, false);
            ps4.executeUpdate();

            PreparedStatement ps5 = conn.prepareStatement(insertUserSql); // Create a user
            ps5.setString(1, "john_doe");
            ps5.setString(2, "astroswon");
            ps5.setString(3, "referee");
            ps5.setInt(4, 73);
            ps5.setInt(5, 190);
            ps5.setString(6, null);
            ps5.setBoolean(7, true);
            ps5.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearH2Database(Connection conn) {
        String sql = "DROP ALL OBJECTS";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
