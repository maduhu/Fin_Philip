package com.philip.fin.loan;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.philip.fin.accounting.AccountingDAO;

public class LoanDAO {
	private static final Logger logger = Logger.getLogger(LoanDAO.class);
	
	private Configuration configuration = null;
	private ServiceRegistry sr = null;
	private SessionFactory sf =null;
	private Session ss = null;
	
	public LoanDAO() {
		logger.debug("Construct Loan DAO");
		logger.debug("set configuration..");
		if(configuration == null)configuration=new Configuration(); 
		configuration.configure();
	}
	
	public boolean setup() {
		logger.debug("start to setup the connection to database, first set return to false..");
		boolean b = false;
		
		logger.debug("set configuration..");
		if(configuration == null){
			configuration=new Configuration();
			configuration.configure();
		}
		
		logger.debug("open the session..");
		//if(sr == null)sr =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		if(sf==null)
		{
			sf = configuration.buildSessionFactory();
			ss = sf.openSession();
		} else {
			ss = sf.openSession();
		}	
				
		logger.debug("successly setup the connection");
		
		b = true;
		return b;
	}
	
	public boolean clearup(){
		logger.debug("task finish,close the session");
		boolean b = false;
		
		if(ss!=null&&ss.isConnected())ss.close();
		if(sf!=null&&ss.isConnected())sf.close();
		
		logger.debug("the session successfully closed");
		
		b = true;
		return b;
	}
	
	public int createLoanWithCompany(Loan_Apply_Info loan, Company_Info company) throws LoanException{
		logger.debug("start to create a new loan with company information");
		int loan_id = 0;
		
		this.setup();
		
		try {
			ss.beginTransaction();
			//create loan with no company:
			ss.save(loan);
			loan_id = loan.getId();
			loan.getLoan_info().setId(loan_id);
			
			//create company info:
			ss.save(company);
			loan.setCompany_id(company.getId());
			
			//create loan with company:
			ss.save(loan);
			
			ss.getTransaction().commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			this.clearup();
		}
		
		return loan_id;
	}
	
	public int createLoanWithoutCompany(Loan_Apply_Info loan) throws LoanException {
		logger.debug("start to create a new loan with company information using before");
		int loan_id = 0;
		
		this.setup();
		
		try {
			ss.beginTransaction();
			//create loan with no company:
			ss.save(loan);
			loan_id = loan.getId();
			loan.getLoan_info().setId(loan_id);
			
			ss.save(loan);
			
			ss.getTransaction().commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			this.clearup();
		}
		
		return loan_id;
	}
	
	public Loan_Apply_Info getLoanApply(int loan_id) throws LoanException{
		logger.debug("start to get loan by id");
		Loan_Apply_Info loan = null;
		
		this.setup();
		
		try {
			ss.beginTransaction();
			loan = (Loan_Apply_Info)ss.get(Loan_Apply_Info.class, loan_id);
			
			ss.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			this.clearup();
		}
		
		return loan;
	}
	
	public ArrayList getLoanApplyListByUser(int user_id) throws LoanException{
		logger.debug("to get Loan Applied by user id");
		ArrayList<Loan_Apply_Info> list = new ArrayList();
		
		this.setup();
		
		try {
			ss.beginTransaction();
			String hql = "from loan_apply_info where user_id = ?";
			Query query = ss.createQuery(hql);
			query.setParameter(0, user_id);
			
			list = (ArrayList)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			this.clearup();
		}
		
		return list;
	}
	
	public boolean deleteLoanApply(Loan_Apply_Info loan) throws LoanException{
		logger.debug("to delete the loan's information");
		boolean b = false;
		
		this.setup();
		
		try {
			ss.beginTransaction();
			ss.delete(loan);
			ss.getTransaction().commit();
			
			b = true;
			logger.debug("the loan has been deleted!");
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			this.clearup();
		}
		
		return b;
	}
}
