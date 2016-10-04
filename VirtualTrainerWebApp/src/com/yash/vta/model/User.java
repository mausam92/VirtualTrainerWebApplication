package com.yash.vta.model;

public class User {
	
    private int user_id;
    private String user_name;
    private String user_status;
    private String user_mobile;
    private String user_mail;
    private String user_manager;
    private int role_id;
    private String user_designation;
    private String user_manager_mail;
    
    public User(){
    }
    
	public User(String user_mail) {
		super();
		this.user_mail = user_mail;
	}

	public User(int user_id, String user_name, String user_status,
                  String user_mobile, String user_mail, String user_manager, int role_id) {
           super();
           this.user_id = user_id;
           this.user_name = user_name;
           this.user_status = user_status;
           this.user_mobile = user_mobile;
           this.user_mail = user_mail;
           this.user_manager = user_manager;
           this.role_id = role_id;
    }


    public int getUser_id() {
           return user_id;
    }

    public void setUser_id(int user_id) {
           this.user_id = user_id;
    }

    public String getUser_name() {
           return user_name;
    }

    public void setUser_name(String user_name) {
           this.user_name = user_name;
    }

    public String getUser_status() {
           return user_status;
    }

    public void setUser_status(String user_status) {
           this.user_status = user_status;
    }

    public String getUser_mobile() {
           return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
           this.user_mobile = user_mobile;
    }

    public String getUser_mail() {
           return user_mail;
    }

    public void setUser_mail(String user_mail) {
           this.user_mail = user_mail;
    }

    public String getUser_manager() {
           return user_manager;
    }

    public void setUser_manager(String user_manager) {
           this.user_manager = user_manager;
    }

    public int getRole_id() {
           return role_id;
    }

   public void setRole_id(int role_id) {
           this.role_id = role_id;
    }

   public String getUser_designation() {
	   return user_designation;
   }

   public void setUser_designation(String user_designation) {
	   this.user_designation = user_designation;
   }

   public String getUser_manager_mail() {
	   return user_manager_mail;
   }

   public void setUser_manager_mail(String user_manager_mail) {
	   this.user_manager_mail = user_manager_mail;
   }
   
   

}

