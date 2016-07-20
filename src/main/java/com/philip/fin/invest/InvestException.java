package com.philip.fin.invest;

public class InvestException extends Exception {
	
	private String retCd;
	private String msgDes;
	
	public InvestException(){
		super();
	}
	
	public InvestException(String message){
		super(message);
		msgDes = message;
	}
	
	public InvestException(String retCd, String msgDes){
		super();
		this.retCd = retCd;
		this.msgDes = msgDes;
	}
	
	public InvestException(Exception e){
		super(e);
	}
}
