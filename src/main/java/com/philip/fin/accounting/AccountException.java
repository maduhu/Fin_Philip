package com.philip.fin.accounting;

public class AccountException extends Exception {
	
	private String retCd;
	private String msgDes;
	
	public AccountException(){
		super();
	}
	
	public AccountException(String message){
		super(message);
		msgDes = message;
	}
	
	public AccountException(String retCd, String msgDes){
		super();
		this.retCd = retCd;
		this.msgDes = msgDes;
	}
	
	public AccountException(Exception e){
		super(e);
	}
}
