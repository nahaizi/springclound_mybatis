package com.lili.provider.entity;

import java.io.Serializable;


public class Processpermissionsrc implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String pname;
	private String taskname;
	private Integer priority;
	private String restype;
	private String permition;
	private String parentid;
	private String iscounter;

	private String operate;

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}


	
	
	public String getRestype() {
		return restype;
	}
	public void setRestype(String restype) {
		this.restype = restype;
	}
	public String getPermition() {
		return permition;
	}
	public void setPermition(String permition) {
		this.permition = permition;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getIscounter() {
		return iscounter;
	}

	public void setIscounter(String iscounter) {
		this.iscounter = iscounter;
	}
}
