package com.philip.fin.accounting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountBalance implements Serializable{
	private int id;
	private String account_num;
	private String account_name;
	private BigDecimal account_bal;
	private Date create_time;
	private Date update_time;
	private Account account;
	private String formattedAccount_bal;
	
	public String getFormattedAccount_bal(){
		return account_bal.toString();
	}
	
	public void setFormattedAccount_bal(String amtString){
		account_bal = new BigDecimal(amtString);
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount_num() {
		return account_num;
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public BigDecimal getAccount_bal() {
		return account_bal;
	}
	public void setAccount_bal(BigDecimal account_bal) {
		this.account_bal = account_bal;
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
}
