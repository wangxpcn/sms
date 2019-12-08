package com.roc.sms.bean;

public class MicroNote {
	private int id; 
	private String message; 
	private String createTime; 
	private String selecValue; 
	private boolean edit; 
	private boolean dialogVisible; 	
	private String color; 
	private String bgcolor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getSelecValue() {
		return selecValue;
	}
	public void setSelecValue(String selecValue) {
		this.selecValue = selecValue;
	}
	
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
	public boolean isDialogVisible() {
		return dialogVisible;
	}
	public void setDialogVisible(boolean dialogVisible) {
		this.dialogVisible = dialogVisible;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	} 
	
}
