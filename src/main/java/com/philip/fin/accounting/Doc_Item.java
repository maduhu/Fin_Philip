package com.philip.fin.accounting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Doc_Item implements Serializable{
	private int doc_id = 0;
	
	private int item_id = 0;
	
	private char credit_debit;
	
	private int account_id = 0;
	
	private BigDecimal amount = null;
	
	private Date create_time = null;
	
	private Date update_time = null;

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public char getCredit_debit() {
		return credit_debit;
	}

	public void setCredit_debit(char credit_debit) {
		this.credit_debit = credit_debit;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
