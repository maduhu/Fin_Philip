package com.philip.fin.user;

public class UserException extends Exception {
	private String retCd;
	private String msgDes;
	
	public UserException(){
		super();
	}
	
	public UserException(String message){
		super(message);
		msgDes = message;
	}
	
	public UserException(String retCd, String msgDes){
		super();
		this.retCd = retCd;
		this.msgDes = msgDes;
	}
	
	public UserException(Exception e){
		super(e);
	}
}
