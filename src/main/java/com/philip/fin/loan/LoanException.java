package com.philip.fin.loan;

public class LoanException extends Exception{
	private String retCd;
	private String msgDes;
	
	public LoanException(){
		super();
	}
	
	public LoanException(String message){
		super(message);
		msgDes = message;
	}
	
	public LoanException(String retCd, String msgDes){
		super();
		this.retCd = retCd;
		this.msgDes = msgDes;
	}
	
	public LoanException(Exception e){
		super(e);
	}
}
