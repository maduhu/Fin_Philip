package com.philip.fin.invest;

import java.util.ArrayList;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.philip.fin.basic.HibernateUtil;
import com.philip.fin.loan.Loan_Apply_Info;
import com.philip.fin.loan.Loan_Info;

public class InvestDAO {
	
	private static final Logger logger = Logger.getLogger(InvestDAO.class);
	
	private Configuration configuration = null;
	private ServiceRegistry sr = null;
	private SessionFactory sf =null;
	private Session ss = null;
	
	public InvestDAO() {
		logger.debug("Construct Invest DAO");
		//logger.debug("set configuration..");
		//if(configuration == null)configuration=new Configuration(); 
		//configuration.configure();
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
		if(sf==null){
			sf = configuration.buildSessionFactory();
			ss = sf.openSession();
		} else {
			if (!ss.isConnected()) ss = sf.openSession();
		}	
				
		logger.debug("successly setup the connection");
		
		b = true;
		return b;
	}
	
	public boolean clearup(){
		logger.debug("task finish,close the session");
		boolean b = false;
		
		if(ss!=null&&ss.isConnected())ss.close();
		if(sf!=null)sf.close();
		
		logger.debug("the session successfully closed");
		
		b = true;
		return b;
	}
	
	public int createInvest(Invest invest) throws InvestException{
		int invest_id;
		String hql = null;
		Loan_Info loan = null;
		
		logger.debug("start to create one invest record");
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			//invest added:
			ss.save(invest);
			invest_id = invest.getId();
			
			//update the amount:
			loan = (Loan_Info)ss.get(Loan_Info.class, invest.getLoan());
			loan.setAmount(loan.getAmount().add(invest.getAmount()));
			ss.save(loan);
			
			ss.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new InvestException(e);
		} finally {
			ss.close();
		}
		
		return invest_id;
	}
	
	public HashSet getInvestRecords(int loan_id) throws InvestException {
		HashSet<Invest> investors = null;
		String hql = null;
		
		logger.debug("start to get all the investors invest to " + loan_id + " loan");
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			hql = "from com.philip.fin.invest.Invest where loan = ?";
			Query query = ss.createQuery(hql);
			query.setParameter(1, loan_id);
			
			investors = (HashSet)query.iterate();
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new InvestException(e);
		} finally {
			ss.close();
		}
		
		return investors;
	}
}
