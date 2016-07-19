package com.philip.fin.loan;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

public class Loan_Apply_Info {
	private int id;
	private int user_id;
	private BigDecimal amount;
	private int loan_period;
	private BigDecimal interest;
	private Date raise_end_time;
	private int company_id;
	private String loan_card;
	private String loan_card_bank;
	private Blob loan_card_img;
	private Blob accountant_prove;
	private Blob treasure_prove;
	private Blob financial_report;
	private Blob tax_prove;
	private String loan_investigation;
	private String loan_usage;
	private Blob contract_prove;
	private boolean guaranty_flag;
	private String guaranty_name;
	private Blob guaranty_prove;
	private Blob guaranty_evaluation;
	private int warrant;
	private Loan_Info loan_info=null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getLoan_period() {
		return loan_period;
	}
	public void setLoan_period(int loan_period) {
		this.loan_period = loan_period;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public Date getRaise_end_time() {
		return raise_end_time;
	}
	public void setRaise_end_time(Date raise_end_time) {
		this.raise_end_time = raise_end_time;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getLoan_card() {
		return loan_card;
	}
	public void setLoan_card(String loan_card) {
		this.loan_card = loan_card;
	}
	public String getLoan_card_bank() {
		return loan_card_bank;
	}
	public void setLoan_card_bank(String loan_card_bank) {
		this.loan_card_bank = loan_card_bank;
	}
	public Blob getLoan_card_img() {
		return loan_card_img;
	}
	public void setLoan_card_img(Blob loan_card_img) {
		this.loan_card_img = loan_card_img;
	}
	public Blob getAccountant_prove() {
		return accountant_prove;
	}
	public void setAccountant_prove(Blob accountant_prove) {
		this.accountant_prove = accountant_prove;
	}
	public Blob getTreasure_prove() {
		return treasure_prove;
	}
	public void setTreasure_prove(Blob treasure_prove) {
		this.treasure_prove = treasure_prove;
	}
	public Blob getFinancial_report() {
		return financial_report;
	}
	public void setFinancial_report(Blob financial_report) {
		this.financial_report = financial_report;
	}
	public Blob getTax_prove() {
		return tax_prove;
	}
	public void setTax_prove(Blob tax_prove) {
		this.tax_prove = tax_prove;
	}
	public String getLoan_investigation() {
		return loan_investigation;
	}
	public void setLoan_investigation(String loan_investigation) {
		this.loan_investigation = loan_investigation;
	}
	public String getLoan_usage() {
		return loan_usage;
	}
	public void setLoan_usage(String loan_usage) {
		this.loan_usage = loan_usage;
	}
	public Blob getContract_prove() {
		return contract_prove;
	}
	public void setContract_prove(Blob contract_prove) {
		this.contract_prove = contract_prove;
	}
	public boolean isGuaranty_flag() {
		return guaranty_flag;
	}
	public void setGuaranty_flag(boolean guaranty_flag) {
		this.guaranty_flag = guaranty_flag;
	}
	public String getGuaranty_name() {
		return guaranty_name;
	}
	public void setGuaranty_name(String guaranty_name) {
		this.guaranty_name = guaranty_name;
	}
	public Blob getGuaranty_prove() {
		return guaranty_prove;
	}
	public void setGuaranty_prove(Blob guaranty_prove) {
		this.guaranty_prove = guaranty_prove;
	}
	public Blob getGuaranty_evaluation() {
		return guaranty_evaluation;
	}
	public void setGuaranty_evaluation(Blob guaranty_evaluation) {
		this.guaranty_evaluation = guaranty_evaluation;
	}
	public int getWarrant() {
		return warrant;
	}
	public void setWarrant(int warrant) {
		this.warrant = warrant;
	}
	public Loan_Info getLoan_info() {
		return loan_info;
	}
	public void setLoan_info(Loan_Info loan_info) {
		this.loan_info = loan_info;
	}
}
