package com.philip.fin.accounting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

public class AccountingDAOTest extends TestCase {
	
	private AccountingDAO dao = null;
	
	protected void setUp() throws Exception {
		dao = new AccountingDAO();
		dao.setup();
	}
	
	protected void tearDown() throws Exception {
		dao.clearup();
	}
	
	public void testAccount() throws Exception {
		int account_id = 0;
		//Construt the account:
		Account account = new Account();
		account.setAccount_num("0000100001");
		account.setAccount_name("user_01_saving");
		account.setChinese_name("陈鹏的存款账户");
		account.setDescription("robbin's account used as saving account");
		account.setAccount_type(AccountingManager.ACCOUNT_TYPE_USER_DEPOSIT);
		account.setType_description("used depos");
		account.setCreate_time(new Date());
		account.setUpdate_time(new Date());
		account.setUser_account(2);
		
		//start to save it:
		account_id = dao.createAccount(account);
		
		//retrive it from database:
		account = null;
		account = dao.getAccount(account_id);
		
		assertEquals(account.getAccount_num(),"0000100001");
		assertEquals(account.getAccount_name(),"user_01_saving");
		assertEquals(account.getChinese_name(),"陈鹏的存款账户");
		assertEquals(account.getDescription(),"robbin's account used as saving account");
		assertEquals(account.getAccount_type(), AccountingManager.ACCOUNT_TYPE_USER_DEPOSIT);
		assertEquals(account.getType_description(), "used depos");
		assertEquals(account.getUser_account(),2);
		
		//retrive by user_id and account_type:
		account = null;
		account = dao.getAccount(2, AccountingManager.ACCOUNT_TYPE_USER_DEPOSIT);
		assertEquals(account.getAccount_num(),"0000100001");
		assertEquals(account.getAccount_name(),"user_01_saving");
		assertEquals(account.getChinese_name(),"陈鹏的存款账户");
		assertEquals(account.getDescription(),"robbin's account used as saving account");
		assertEquals(account.getAccount_type(), AccountingManager.ACCOUNT_TYPE_USER_DEPOSIT);
		assertEquals(account.getType_description(), "used depos");
		assertEquals(account.getUser_account(),2);
		
		//delete it from database:
		dao.deleteAccount(account);
	}
	
	public void testDocument() throws Exception {
		//1、test add document:
		int doc_id;
		//Construct the items:
		HashSet items = new HashSet();
		Doc_Item item1 = new Doc_Item();
		item1.setItem_id(1);
		item1.setAccount_id(2);
		item1.setCredit_debit('c');
		item1.setAmount(new BigDecimal(50));
		item1.setCreate_time(new Date());
		item1.setUpdate_time(new Date());
		items.add(item1);
		
		Doc_Item item2 = new Doc_Item();
		item2.setItem_id(2);
		item2.setAccount_id(2);
		item2.setCredit_debit('c');
		item2.setAmount(new BigDecimal(50));
		item2.setCreate_time(new Date());
		item2.setUpdate_time(new Date());
		items.add(item2);
		
		Doc_Item item3 = new Doc_Item();
		item3.setItem_id(3);
		item3.setAccount_id(1);
		item3.setCredit_debit('d');
		item3.setAmount(new BigDecimal(100));
		item3.setCreate_time(new Date());
		item3.setUpdate_time(new Date());
		items.add(item3);
		
		//Construct the document:
		Document document = new Document();
		document.setBusiness_event(0);
		document.setDescription("test to post the document");
		document.setDoc_items(items);
		document.setCreate_time(new Date());
		document.setUpdate_time(new Date());
		
		doc_id = dao.postDocument(document);
		
		assertEquals(new Integer(1),new Integer(1));
		
		//2、test get document:
		Document doc = new Document();
		doc = dao.getDocument(doc_id);
		
		assertEquals(doc.getBusiness_event(),0);
		assertEquals(doc.getDescription(),"test to post the document");
		assertEquals(doc.getDoc_items().size(),3);
		
		Iterator i = doc.getDoc_items().iterator();
		Doc_Item ditem1=(Doc_Item)(i.next());
		assertEquals(ditem1.getItem_id(),1);
		assertEquals(ditem1.getAccount_id(),2);
		assertEquals(ditem1.getCredit_debit(),'c');
		assertEquals(ditem1.getAmount(),new BigDecimal(50).setScale(2));
		
		Doc_Item ditem2=(Doc_Item)(i.next());
		assertEquals(ditem2.getItem_id(),2);
		assertEquals(ditem2.getAccount_id(),2);
		assertEquals(ditem2.getCredit_debit(),'c');
		assertEquals(ditem2.getAmount(),new BigDecimal(50).setScale(2));
		
		Doc_Item ditem3=(Doc_Item)(i.next());
		assertEquals(ditem3.getItem_id(),3);
		assertEquals(ditem3.getAccount_id(),1);
		assertEquals(ditem3.getCredit_debit(),'d');
		assertEquals(ditem3.getAmount(),new BigDecimal(100).setScale(2));
		
		//test delete:
		dao.deleteDocument(doc);
	}
}
