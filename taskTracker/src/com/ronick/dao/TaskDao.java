package com.ronick.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ronick.beans.TaskItem;

public class TaskDao implements TaskInterfaceDao {
	private MysqlDaoFactory mdf;
	
	

	public TaskDao(MysqlDaoFactory mdf) {
		super();
		this.mdf = mdf;
	}

	@Override
	public ArrayList<TaskItem> getTasks() {
		
		ArrayList<TaskItem> liste = new ArrayList<>();
		
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = mdf.getConnection().createStatement();
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
		}finally {
			try {
				if(result != null) {
					result.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(mdf.getConnection() != null) {
					mdf.getConnection().close();
				}
				
			} catch(SQLException exc) {
				
			}
		}
		
		return liste;
	}

	@Override
	public TaskItem getTaskById(int id) {
		PreparedStatement statement = null;
		ResultSet result = null;
		TaskItem task = new TaskItem();
		
		try {
			statement = mdf.getConnection().prepareStatement("SELECT id, label,date,active FROM task WHERE active=1 AND id=?;");
			statement.setInt(1,  id);
			result = statement.executeQuery();
			if(result.next()) {
				task.setId(Integer.parseInt( result.getString("id") ));
				task.setLabel((String)  result.getString("label"));
				task.setDate((String)  result.getString("date"));
				task.setActive(Integer.parseInt( result.getString("active") ));
			}
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}finally {
			try {
				if(result != null) {
					result.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(mdf.getConnection() != null) {
					mdf.getConnection().close();
				}
				
			} catch(SQLException exc) {
				
			}
		}
		
		return task;
	}

	@Override
	public void addTask(TaskItem task) {
		PreparedStatement ps=null;
		try {
			ps = mdf.getConnection().prepareStatement("INSERT INTO task(label,date) VALUES(?,?);");
			ps.setString(1, task.getLabel());
			ps.setString(2, task.getDate());
			int n = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(ps != null) {
					ps.close();
				}
				if(mdf.getConnection() != null) {
					mdf.getConnection().close();
				}
				
			} catch(SQLException exc) {
				
			}
		}
		
		 
		
		
		

	}

	@Override
	public void editTask(TaskItem task) {
		PreparedStatement ps=null;
		try {
			 ps = mdf.getConnection().prepareStatement("UPDATE task SET label=?,date=?,active=? WHERE id=?;");
			
			ps.setString(1, task.getLabel());
			ps.setString(2, task.getDate());
			ps.setInt(3, task.getActive());
			ps.setInt(4, task.getId());
			
			int n = ps.executeUpdate();
		}catch(SQLException e) {
			
		}finally {
			try {
				
				if(ps != null) {
					ps.close();
				}
				if(mdf.getConnection() != null) {
					mdf.getConnection().close();
				}
				
			} catch(SQLException exc) {
				
			}
		}
	}

}
