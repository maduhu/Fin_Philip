package com.philip.fin.loan;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

public class Company_Info {
	private int id;
	private String company_name;
	private char company_loan_role;
	private String company_address;
	private char company_type;
	private String business;
	private Blob business_passport;
	private Blob business_code;
	private Blob tax_code;
	private Blob open_passport;
	private BigDecimal reg_capital;
	private String representive;
	private Blob representive_prove;
	private int employee_num;
	private Date start_date;
	private Date create_date;
	private Date update_date;
	private int loan_record;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public char getCompany_loan_role() {
		return company_loan_role;
	}
	public void setCompany_loan_role(char company_loan_role) {
		this.company_loan_role = company_loan_role;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public char getCompany_type() {
		return company_type;
	}
	public void setCompany_type(char company_type) {
		this.company_type = company_type;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public Blob getBusiness_passport() {
		return business_passport;
	}
	public void setBusiness_passport(Blob business_passport) {
		this.business_passport = business_passport;
	}
	public Blob getBusiness_code() {
		return business_code;
	}
	public void setBusiness_code(Blob business_code) {
		this.business_code = business_code;
	}
	public Blob getTax_code() {
		return tax_code;
	}
	public void setTax_code(Blob tax_code) {
		this.tax_code = tax_code;
	}
	public Blob getOpen_passport() {
		return open_passport;
	}
	public void setOpen_passport(Blob open_passport) {
		this.open_passport = open_passport;
	}
	public BigDecimal getReg_capital() {
		return reg_capital;
	}
	public void setReg_capital(BigDecimal reg_capital) {
		this.reg_capital = reg_capital;
	}
	public String getRepresentive() {
		return representive;
	}
	public void setRepresentive(String representive) {
		this.representive = representive;
	}
	public Blob getRepresentive_prove() {
		return representive_prove;
	}
	public void setRepresentive_prove(Blob representive_prove) {
		this.representive_prove = representive_prove;
	}
	public int getEmployee_num() {
		return employee_num;
	}
	public void setEmployee_num(int employee_num) {
		this.employee_num = employee_num;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
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
	public int getLoan_record() {
		return loan_record;
	}
	public void setLoan_record(int loan_record) {
		this.loan_record = loan_record;
	}
	
}
