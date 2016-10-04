package com.yash.vta.model;

public class Privilege {

	private int privilege_id;
	private String privilege_name;
	
	public Privilege(){
		
	}

	public Privilege(int privilege_id, String privilege_name) {
		super();
		this.privilege_id = privilege_id;
		this.privilege_name = privilege_name;
	}

	public int getPrivilege_id() {
		return privilege_id;
	}

	public void setPrivilege_id(int privilege_id) {
		this.privilege_id = privilege_id;
	}

	public String getPrivilege_name() {
		return privilege_name;
	}

	public void setPrivilege_name(String privilege_name) {
		this.privilege_name = privilege_name;
	}

}

