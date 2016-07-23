package com.philip.fin.event;

public class EventException extends Exception{
		
		private String retCd;
		private String msgDes;
		
		public EventException(){
			super();
		}
		
		public EventException(String message){
			super(message);
			msgDes = message;
		}
		
		public EventException(String retCd, String msgDes){
			super();
			this.retCd = retCd;
			this.msgDes = msgDes;
		}
		
		public EventException(Exception e){
			super(e);
		}

}
