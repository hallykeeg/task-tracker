package com.ronick.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ronick.beans.TaskItem;


 

public class DB {
	private Connection connection;
	
	public ArrayList<TaskItem> getTasks(){
		ArrayList<TaskItem> liste = new ArrayList<>();
		
		Statement statement = null;
		ResultSet result = null;
		
		loadDB();
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT id, label,date,active FROM task WHERE active=1;");
			while( result.next() ) {
				int id = Integer.parseInt( result.getString("id") );
				String label = (String)  result.getString("label");
				String date = (String)  result.getString("date");
				int active = Integer.parseInt( result.getString("active") );
				liste.add(new TaskItem(id,label,date,active));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				if(result != null) {
					result.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(connection != null) {
					connection.close();
				}
				
			} catch(SQLException exc) {
				
			}
		}
		
		return liste;
	}
	public TaskItem getTaskById(int id) {
		loadDB();
		PreparedStatement statement = null;
		ResultSet result = null;
		TaskItem task = new TaskItem();
		try {
			statement = connection.prepareStatement("SELECT id, label,date,active FROM task WHERE active=1 AND id=?;");
			statement.setInt(1,  id);
			result = statement.executeQuery();
			if(result.next()) {
				int idTask = Integer.parseInt( result.getString("id") );
				String label = (String)  result.getString("label");
				String date = (String)  result.getString("date");
				int active = Integer.parseInt( result.getString("active") );
				task.setId(idTask);
				task.setLabel(label);
				task.setDate(date);
				task.setActive(active);
				
			}
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				if(result != null) {
					result.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(connection != null) {
					connection.close();
				}
				
			} catch(SQLException exc) {
				
			}
		}
		return task;
		
	}
	public void addTask(TaskItem task) {			
			loadDB();
			
			try {
				
				PreparedStatement ps = connection.prepareStatement("INSERT INTO task(label,date) VALUES(?,?);");
				ps.setString(1, task.getLabel());
				ps.setString(2, task.getDate());
				 
				
				int n = ps.executeUpdate();
				
			} catch (SQLException e) {
				 
				e.printStackTrace();
			}
			
		}
	public void editTask(TaskItem task) {			
		loadDB();
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("UPDATE task SET label=?,date=?,active=? WHERE id=?;");
			
			ps.setString(1, task.getLabel());
			ps.setString(2, task.getDate());
			ps.setInt(3, task.getActive());
			ps.setInt(4, task.getId());
			
			int n = ps.executeUpdate();
			
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		
	}
	
	
	private void loadDB() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {				
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee","root","");
		} catch (SQLException e) {				
			e.printStackTrace();
		}
	
}

}
