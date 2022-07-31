package com.ronick.beans;

public class TaskItem {
	private int id;
	private String label;
	private String date;
	private int active;
	
	
	
	public TaskItem(int id, String label, String date, int active) {
		super();
		this.id = id;
		this.label = label;
		this.date = date;
		this.active = active;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
