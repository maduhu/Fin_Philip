package com.philip.fin.loan;

import java.math.BigDecimal;
import java.util.Date;

public class Loan_Info {
	private int id;
	private String loan_name;
	private char verify_status;
	private int loan_period;
	private BigDecimal amount;
	private BigDecimal annual_interest_loan;
	private BigDecimal annual_interest_invest;
	private Date raise_start_day;
	private Date raise_end_day;
	private BigDecimal raised_amount;
	private int loan_level;
	private String loan_description;
	private BigDecimal minimum_raise_amount;
	private BigDecimal left_amount;
	private Loan_Apply_Info loan_apply;
	private float percentage;
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	public Loan_Apply_Info getLoan_apply() {
		return loan_apply;
	}
	public void setLoan_apply(Loan_Apply_Info loan_apply) {
		this.loan_apply = loan_apply;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoan_name() {
		return loan_name;
	}
	public void setLoan_name(String loan_name) {
		this.loan_name = loan_name;
	}
	public char getVerify_status() {
		return verify_status;
	}
	public void setVerify_status(char verify_status) {
		this.verify_status = verify_status;
	}
	public int getLoan_period() {
		return loan_period;
	}
	public void setLoan_period(int loan_period) {
		this.loan_period = loan_period;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getAnnual_interest_loan() {
		return annual_interest_loan;
	}
	public void setAnnual_interest_loan(BigDecimal annual_interest_loan) {
		this.annual_interest_loan = annual_interest_loan;
	}
	public BigDecimal getAnnual_interest_invest() {
		return annual_interest_invest;
	}
	public void setAnnual_interest_invest(BigDecimal annual_interest_invest) {
		this.annual_interest_invest = annual_interest_invest;
	}
	public Date getRaise_start_day() {
		return raise_start_day;
	}
	public void setRaise_start_day(Date raise_start_day) {
		this.raise_start_day = raise_start_day;
	}
	public Date getRaise_end_day() {
		return raise_end_day;
	}
	public void setRaise_end_day(Date raise_end_day) {
		this.raise_end_day = raise_end_day;
	}
	public BigDecimal getRaised_amount() {
		return raised_amount;
	}
	public void setRaised_amount(BigDecimal raised_amount) {
		this.raised_amount = raised_amount;
	}
	public int getLoan_level() {
		return loan_level;
	}
	public void setLoan_level(int loan_level) {
		this.loan_level = loan_level;
	}
	public String getLoan_description() {
		return loan_description;
	}
	public void setLoan_description(String loan_description) {
		this.loan_description = loan_description;
	}
	public BigDecimal getMinimum_raise_amount() {
		return minimum_raise_amount;
	}
	public void setMinimum_raise_amount(BigDecimal minimum_raise_amount) {
		this.minimum_raise_amount = minimum_raise_amount;
	}
	public BigDecimal getLeft_amount() {
		return left_amount;
	}
	public void setLeft_amount(BigDecimal left_amount) {
		this.left_amount = left_amount;
	}
	
}
