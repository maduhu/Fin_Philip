package com.philip.fin.accounting;

public class AccountConstants {
	//the business types:
	public static final int BIZ_OPER_NO_OPERATION = 0;
	public static final int BIZ_OPER_CREATE_USER_ACCOUNT = 1;
	public static final int BIZ_OPER_DEPOSIT_MONEY = 2;
	public static final int BIZ_OPER_DRAW_MONEY = 3;
	public static final int BIZ_OPER_INVEST_MONEY = 4;
	public static final int BIZ_OPER_RAISE_FAILED_RETURN = 5;
	
	//the account types:
	public static final int ACCOUNT_TYPE_PLATFORM_ACCOUNT = 1;
	public static final int ACCOUNT_TYPE_USER_LOAN = 2;
	public static final int ACCOUNT_TYPE_USER_ACCOUNT_PAYABLE = 3;
	public static final int ACCOUNT_TYPE_BANK_ACCOUNT = 4;
	public static final int ACCOUNT_TYPE_PLATFORM_FEE = 5;
	public static final int ACCOUNT_TYPE_USER_DEPOSIT = 6;
	public static final int ACCOUNT_TYPE_USER_INVEST = 7;
	public static final int ACCOUNT_TYPE_PROVISION_LOSS = 8;
	
	//some constant account:
	public static final int ACCOUNT_PLATFORM_ACCOUNT = 0;
}
