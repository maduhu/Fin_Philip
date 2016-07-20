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
	private String business_passport_path;
	private Blob business_passport;
	private String business_policy_path;
	private Blob business_policy;
	private String business_code_path;
	private Blob business_code;
	private String tax_code_path;
	private Blob tax_code;
	private String open_passport_path;
	private Blob open_passport;
	private BigDecimal reg_capital;
	private String representive;
	private String representive_prove_path;
	private Blob representive_prove;
	private int employee_num;
	private Date start_date;
	private Date create_date;
	private Date update_date;
	private int loan_record;
	private int loan_id;
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
	public String getBusiness_policy_path() {
		return business_policy_path;
	}
	public void setBusiness_policy_path(String business_policy_path) {
		this.business_policy_path = business_policy_path;
	}
	public Blob getBusiness_policy() {
		return business_policy;
	}
	public void setBusiness_policy(Blob business_policy) {
		this.business_policy = business_policy;
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
	public String getBusiness_passport_path() {
		return business_passport_path;
	}
	public void setBusiness_passport_path(String business_passport_path) {
		this.business_passport_path = business_passport_path;
	}
	public String getBusiness_code_path() {
		return business_code_path;
	}
	public void setBusiness_code_path(String business_code_path) {
		this.business_code_path = business_code_path;
	}
	public String getTax_code_path() {
		return tax_code_path;
	}
	public void setTax_code_path(String tax_code_path) {
		this.tax_code_path = tax_code_path;
	}
	public String getOpen_passport_path() {
		return open_passport_path;
	}
	public void setOpen_passport_path(String open_passport_path) {
		this.open_passport_path = open_passport_path;
	}
	public String getRepresentive_prove_path() {
		return representive_prove_path;
	}
	public void setRepresentive_prove_path(String representive_prove_path) {
		this.representive_prove_path = representive_prove_path;
	}
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	
}
