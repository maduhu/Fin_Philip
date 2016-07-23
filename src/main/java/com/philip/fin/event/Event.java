package com.philip.fin.event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Event implements Serializable{
	private int id;
	private int business_type;
	private BigDecimal amount_total;
	private Date create_date;
	private Date update_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(int business_type) {
		this.business_type = business_type;
	}
	public BigDecimal getAmount_total() {
		return amount_total;
	}
	public void setAmount_total(BigDecimal amount_total) {
		this.amount_total = amount_total;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
}
