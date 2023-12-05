package com.bec.oop;

import com.bec.oop.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Students {

    Connection conn;
    Database db = new Database();

    public void viewAll() {
        try {
            String sql = "SELECT * FROM students";
            conn = db.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("firstname") + " " + rs.getString("lastname"));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void viewStudent(String lastname) {
        try {
            String sql = "SELECT * FROM students WHERE lastname = ?";
            conn = db.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lastname);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("firstname") + " " + rs.getString("lastname"));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void insertStudent(String firstName, String lastName, String address) {
        try {
            String sql = "INSERT INTO students (firstname, lastname, address) VALUES (?, ?, ?)";
            conn = db.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, address);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Student inserted.");
            } else {
                System.out.println("There were errors while inserting the student.");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateStudent(String id, String newFirstName, String newLastName, String newAddress) {
        try {
            String sql = "UPDATE students SET firstname=?, lastname=?, address=? WHERE id=?";
            conn = db.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newFirstName);
            ps.setString(2, newLastName);
            ps.setString(3, newAddress);
            ps.setString(4, id);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Student updated.");
            } else {
                System.out.println("There were errors while updating the student.");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void deleteStudent(String id) {
        try {
            String sql = "DELETE FROM students WHERE id = ?";
            conn = db.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("Student deleted.");
            } else {
                System.out.println("There were errors while deleting the student.");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}