package com.philip.fin.accounting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountBalHistory implements Serializable{
	private int id;
	private String account_num;
	private Date date;
	private String account_name;
	private BigDecimal account_bal;
	private char D_C;
	
	public char getD_C() {
		return D_C;
	}
	public void setD_C(char d_C) {
		D_C = d_C;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
}
