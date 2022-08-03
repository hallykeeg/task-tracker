package com.ronick.dao;

import java.util.ArrayList;

import com.ronick.beans.TaskItem;

public interface TaskInterfaceDao {
	
	  ArrayList<TaskItem> getTasks();
	  TaskItem getTaskById(int id) ;
	  void addTask(TaskItem task);
	  void editTask(TaskItem task);

}
