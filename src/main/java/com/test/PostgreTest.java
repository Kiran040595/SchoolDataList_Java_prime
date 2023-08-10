package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreTest {
	public static void main(String args[]) {
		System.out.println("Hello World!!!");
		
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root")) {
			 
            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.todo");
            while (resultSet.next()) {
                System.out.println("id " + resultSet.getString("id") + " description " + resultSet.getString("description") + " priority " + resultSet.getString("priority") + " title " + resultSet.getString("title"));
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
	}
}