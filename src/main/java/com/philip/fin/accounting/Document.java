package com.philip.fin.accounting;

import java.util.Date;
import java.util.Set;

public class Document {
	private int id;
	
	private int business_event;
	
	private String description;
	
	private Date create_time;
	
	private Date update_time;
	
	private Set doc_items;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusiness_event() {
		return business_event;
	}

	public void setBusiness_event(int business_event) {
		this.business_event = business_event;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set getDoc_items() {
		return doc_items;
	}

	public void setDoc_items(Set doc_items) {
		this.doc_items = doc_items;
	}
}
