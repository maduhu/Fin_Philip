package com.philip.fin.accounting;

public class CheckInfo {
	private boolean balance;
	private int account_id;
	private String account_num;
	public boolean isBalance() {
		return balance;
	}
	public void setBalance(boolean balance) {
		this.balance = balance;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getAccount_num() {
		return account_num;
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}
}
