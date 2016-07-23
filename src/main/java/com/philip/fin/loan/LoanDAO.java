package com.philip.fin.loan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.philip.fin.accounting.AccountingDAO;
import com.philip.fin.basic.HibernateUtil;

public class LoanDAO {
	private static final Logger logger = Logger.getLogger(LoanDAO.class);
	
	private Configuration configuration = null;
	private ServiceRegistry sr = null;
	private SessionFactory sf =null;
	private Session ss = null;
	
	public LoanDAO() {
		logger.debug("Construct Loan DAO");
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
	
	public int createCompany(Company_Info company) throws LoanException{
		logger.debug("start to create a company information");
		int loan_id = 0;
		InputStream in = null;
		Blob blob = null;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			//create loan with no company:
			
			//solve the blob:
			//1.business_passport:
			if(company.getBusiness_passport_path()!=null){
				in = new FileInputStream(company.getBusiness_passport_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setBusiness_passport(blob);
			}
			
			//2.business_policy:
			if(company.getBusiness_policy_path()!=null){
				in = new FileInputStream(company.getBusiness_policy_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setBusiness_passport(blob);
			}
			
			//3.business_code:
			if(company.getBusiness_code_path()!=null){
				in = new FileInputStream(company.getBusiness_code_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setBusiness_code(blob);
			}
			
			
			//4.tax_code:
			if(company.getTax_code_path()!=null){
				in = new FileInputStream(company.getTax_code_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setTax_code(blob);
			}
			
			//5.open_passport:
			if(company.getOpen_passport()!=null){
				in = new FileInputStream(company.getOpen_passport_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setOpen_passport(blob);
			}
			
			//6.representive_prove:
			if(company.getRepresentive_prove_path()!=null){
				in = new FileInputStream(company.getRepresentive_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setBusiness_passport(blob);
			}
		
			ss.save(company);
			
			ss.getTransaction().commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			ss.close();
		}
		
		return loan_id;
	}
	
	public int createLoanWithoutCompany(Loan_Apply_Info loan) throws LoanException {
		logger.debug("start to create a new loan with company information using before");
		int loan_id = 0;
		InputStream in = null;
		Blob blob = null;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			//create loan with no company:
			ss.save(loan);
			loan_id = loan.getId();
			loan.getLoan_info().setId(loan_id);
			
			//solve the blob:
			//1.loan_bank_card_img:
			if(loan.getLoan_card_img_path()!=null){
				in = new FileInputStream(loan.getLoan_card_img_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setLoan_card_img(blob);
			}
				
			//2.accountant_prove:
			if(loan.getAccountant_prove()!=null){
				in = new FileInputStream(loan.getAccountant_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setAccountant_prove(blob);
			}
			
			//3.treasure_prove:
			if(loan.getTreasure_prove_path()!=null){
				in = new FileInputStream(loan.getTreasure_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setTreasure_prove(blob);
			}

			//4.financial_report:
			if(loan.getFinancial_report_path()!=null){
				in = new FileInputStream(loan.getFinancial_report_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setFinancial_report(blob);
			}
			
			//5.tax_report:
			if(loan.getTax_prove_path()!=null){
				in = new FileInputStream(loan.getTax_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setTax_prove(blob);
			}

			//6.contract_prove:
			if(loan.getContract_prove_path()!=null){
				in = new FileInputStream(loan.getContract_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setContract_prove(blob);
			}

			//7.guaranty_prove:
			if(loan.getGuaranty_prove_path()!=null){
				in = new FileInputStream(loan.getGuaranty_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setGuaranty_prove(blob);
			}
			
			//8.guaranty_evaluation:
			if(loan.getGuaranty_evaluation_path()!=null){
				in = new FileInputStream(loan.getGuaranty_evaluation_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setGuaranty_evaluation(blob);
			}

			ss.save(loan);
			
			ss.getTransaction().commit();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			ss.close();
		}
		
		return loan_id;
	}
	
	public Loan_Apply_Info getLoanApply(int loan_id) throws LoanException{
		logger.debug("start to get loan by id");
		Loan_Apply_Info loan = null;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			loan = (Loan_Apply_Info)ss.get(Loan_Apply_Info.class, loan_id);
			
			ss.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			ss.close();
		}
		
		return loan;
	}
	
	public ArrayList getLoanApplyListByUser(int user_id) throws LoanException{
		logger.debug("to get Loan Applied by user id");
		ArrayList<Loan_Apply_Info> list = new ArrayList();
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
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
			ss.close();
		}
		
		return list;
	}
	
	public boolean deleteLoanApply(Loan_Apply_Info loan) throws LoanException{
		logger.debug("to delete the loan's information");
		boolean b = false;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
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
			ss.close();
		}
		
		return b;
	}
	public boolean updateLoanApplyInfo(Loan_Apply_Info loan) throws LoanException{
		logger.debug("start to update a loan using before");
		boolean b = false;
		int loan_id = 0;
		InputStream in = null;
		Blob blob = null;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			
			//solve the blob:
			//1.loan_bank_card_img:
			if(loan.getLoan_card_img_path()!=null){
				in = new FileInputStream(loan.getLoan_card_img_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setLoan_card_img(blob);
			}
				
			//2.accountant_prove:
			if(loan.getAccountant_prove()!=null){
				in = new FileInputStream(loan.getAccountant_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setAccountant_prove(blob);
			}
			
			//3.treasure_prove:
			if(loan.getTreasure_prove_path()!=null){
				in = new FileInputStream(loan.getTreasure_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setTreasure_prove(blob);
			}

			//4.financial_report:
			if(loan.getFinancial_report_path()!=null){
				in = new FileInputStream(loan.getFinancial_report_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setFinancial_report(blob);
			}
			
			//5.tax_report:
			if(loan.getTax_prove_path()!=null){
				in = new FileInputStream(loan.getTax_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setTax_prove(blob);
			}

			//6.contract_prove:
			if(loan.getContract_prove_path()!=null){
				in = new FileInputStream(loan.getContract_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setContract_prove(blob);
			}

			//7.guaranty_prove:
			if(loan.getGuaranty_prove_path()!=null){
				in = new FileInputStream(loan.getGuaranty_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setGuaranty_prove(blob);
			}
			
			//8.guaranty_evaluation:
			if(loan.getGuaranty_evaluation_path()!=null){
				in = new FileInputStream(loan.getGuaranty_evaluation_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				loan.setGuaranty_evaluation(blob);
			}

			ss.save(loan);
			
			ss.getTransaction().commit();
			
			b = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			ss.close();
		}
		
		return b;
	}
	
	public boolean updateLoanInfo(Loan_Info loan) throws LoanException{
		boolean b = false;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			ss.save(loan);
			ss.getTransaction().commit();
			
			b = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			ss.close();
		}
		
		return b;
	}
	
	public boolean updateCompanyInfo(Company_Info company) throws LoanException{
		boolean b = false;
		Blob blob = null;
		InputStream in = null;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			//solve the blob:
			//1.business_passport:
			if(company.getBusiness_passport_path()!=null){
				in = new FileInputStream(company.getBusiness_passport_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setBusiness_passport(blob);
			}
			
			//2.business_policy:
			if(company.getBusiness_policy_path()!=null){
				in = new FileInputStream(company.getBusiness_policy_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setBusiness_passport(blob);
			}
			
			//3.business_code:
			if(company.getBusiness_code_path()!=null){
				in = new FileInputStream(company.getBusiness_code_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setBusiness_code(blob);
			}
			
			
			//4.tax_code:
			if(company.getTax_code_path()!=null){
				in = new FileInputStream(company.getTax_code_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setTax_code(blob);
			}
			
			//5.open_passport:
			if(company.getOpen_passport()!=null){
				in = new FileInputStream(company.getOpen_passport_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setOpen_passport(blob);
			}
			
			//6.representive_prove:
			if(company.getRepresentive_prove_path()!=null){
				in = new FileInputStream(company.getRepresentive_prove_path());
				blob = Hibernate.getLobCreator(ss).createBlob(in, in.available()); 
				company.setBusiness_passport(blob);
			}
			
			ss.beginTransaction();
			ss.save(company);
			ss.getTransaction().commit();
			
			b = true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			ss.close();
		}
		
		return b;
	}
	
	public boolean updateLoanStatus(int loan_id, char status) throws LoanException{
		logger.debug("to update the loan verify status");
		boolean b = false;
		String hql = null;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			
			hql = "update com.philip.fin.loan.Loan_Info set verify_status = :status where id = :id";
			Query query = ss.createQuery(hql);
			query.setParameter("status", status);
			query.setParameter("id", loan_id);
			
			int result = query.executeUpdate();
			ss.getTransaction().commit();
			
			if (result != 0)b = true;
			logger.debug("successfully update the status");
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			ss.close();
		}

		return b;
	}
	
	public boolean updateLoan_Company(Company_Info company, int loan_id) throws LoanException {
		logger.debug("to update the loan 's company");
		boolean b = false;
		String hql = null;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			
			hql = "update com.philip.fin.loan.Loan_Apply set company_id = :company_id where id = :id";
			Query query = ss.createQuery(hql);
			query.setParameter("company_id", company.getId());
			query.setParameter("id", loan_id);
			
			int result = query.executeUpdate();
			ss.getTransaction().commit();
			
			if (result != 0)b = true;
			logger.debug("successfully connect the company to loan");
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			ss.close();
		}

		return b;
	}
	
	public ArrayList getLoanApplyListByStatus(char status) throws LoanException{
		logger.debug("to get Loan Applied by status");
		ArrayList<Loan_Info> list = new ArrayList();
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			String hql = "from loan_info where verify_status = ?";
			Query query = ss.createQuery(hql);
			query.setParameter(0, status);
			
			list = (ArrayList)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			ss.close();
		}
		
		return list;
	}
	
	public Loan_Info getLoanInfo(int loan_id) throws LoanException{
		logger.debug("to get loan information");
		Loan_Info loan = null;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			loan = (Loan_Info)ss.get(Loan_Info.class, loan_id);
			ss.getTransaction().commit();
			
			loan.setPercentage(loan.getRaised_amount().divide(loan.getAmount()).floatValue());
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.error(e);
			throw new LoanException(e);
		} finally {
			ss.close();
		}
		
		return loan;
	}
}
