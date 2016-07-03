package com.philip.fin.user;

import java.util.Date;

public class User {
	private int id;
	private String user_name;
	private String chinese_name;
	private String password;
	private String mobile;
	private String mail_address;
	private Date create_time;
	private Date update_time;
	private Date last_login;
	private int last_operation;
	private int investment_account;
	private int saving_account;
	private int loan_account;
	private boolean alive_flag;
	private Date delete_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getChinese_name() {
		return chinese_name;
	}
	public void setChinese_name(String chinese_name) {
		this.chinese_name = chinese_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Date getLast_login() {
		return last_login;
	}
	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
	public int getLast_operation() {
		return last_operation;
	}
	public void setLast_operation(int last_operation) {
		this.last_operation = last_operation;
	}
	public int getInvestment_account() {
		return investment_account;
	}
	public void setInvestment_account(int investment_account) {
		this.investment_account = investment_account;
	}
	public int getSaving_account() {
		return saving_account;
	}
	public void setSaving_account(int saving_account) {
		this.saving_account = saving_account;
	}
	public int getLoan_account() {
		return loan_account;
	}
	public void setLoan_account(int loan_account) {
		this.loan_account = loan_account;
	}
	public boolean isAlive_flag() {
		return alive_flag;
	}
	public void setAlive_flag(boolean alive_flag) {
		this.alive_flag = alive_flag;
	}
	public Date getDelete_time() {
		return delete_time;
	}
	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}
}
