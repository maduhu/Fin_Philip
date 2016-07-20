package com.philip.fin.loan;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.hibernate.Hibernate;

import junit.framework.TestCase;

public class LoanDAOTest extends TestCase{
	//Constants:
	private static final String LOAN_BANK_CARD_IMG = "loan_card.jpg";
	private static final String ACCOUNTANT_PROVE_IMG = "accountant_prove.jpg";
	private static final String TREASURE_PROVE_IMG = "treasure_prove.jpg";
	private static final String FINANCIAL_REPORT_IMG = "financial_report.jpg";
	private static final String TAX_PROVE_IMG = "tax_prove.jpg";
	private static final String CONTRACT_PROVE_IMG = "contract_prove.jpg";
	private static final String GUARANTY_PROVE_IMG = "guaranty_prove.jpg";
	private static final String GUARANTY_EVALUATION_IMG = "guaranty_Evaluation.jpg";
	
	private static final String BUSINESS_PASSPORT_IMG = "business_passport.jpg";
	private static final String BUSINESS_POLICY_IMG = "business_policy.jpg";
	private static final String BUSINESS_CODE_IMG = "business_code.jpg";
	private static final String TAX_CODE_IMG = "tax_code.jpg";
	private static final String REPRESENTIVE_PROVE_IMG = "representive_prove.jpg";
	
	private LoanDAO dao = null;
	
	protected void setUp() throws Exception{
		dao = new LoanDAO();	
		dao.setup();
	}
	
	protected void tearDown() throws Exception {
		dao.clearup();
	}
	
	public void testLoan() throws LoanException, SQLException, IOException{
		Loan_Apply_Info loan_apply = new Loan_Apply_Info();
		Loan_Info loan_info = new Loan_Info();
		Company_Info company = new Company_Info();
		String filePath = null;
		int loan_id;
		Date date = new Date();
		
		//create loan without company:
		loan_apply.setUser_id(1);
		loan_apply.setAmount(new BigDecimal(10000000));
		loan_apply.setLoan_period(12);
		loan_apply.setInterest(new BigDecimal(12.50));
		//loan_apply.setRaise_end_time(date);
		loan_apply.setCompany_id(0);
		loan_apply.setLoan_card("62220011012285741");
		loan_apply.setLoan_card_bank("浙商银行");	
		//filePath = new File("").getAbsolutePath() + "\\src\\test\\resources\\" +this.LOAN_BANK_CARD_IMG;
		loan_apply.setLoan_card_img_path(filePath);
		//filePath = new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.ACCOUNTANT_PROVE_IMG;
		loan_apply.setAccountant_prove_path(filePath);
		//filePath = new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.TREASURE_PROVE_IMG;
		loan_apply.setTreasure_prove_path(filePath);
		//filePath = new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.FINANCIAL_REPORT_IMG;
		loan_apply.setFinancial_report_path(filePath);
		//filePath = new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.TAX_PROVE_IMG;
		loan_apply.setTax_prove_path(filePath);
		loan_apply.setLoan_investigation("这个项目比较好，公司有较好的收入，现金流稳定。");
		loan_apply.setLoan_usage("该资金主要用于补充公司经营性现金流");
		//filePath = new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.CONTRACT_PROVE_IMG;
		loan_apply.setContract_prove_path(filePath);
		loan_apply.setGuaranty_flag(true);
		loan_apply.setGuaranty_name("恒生电子集团");
		//filePath = new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.GUARANTY_PROVE_IMG;
		loan_apply.setGuaranty_prove_path(filePath);
		//filePath = new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.GUARANTY_EVALUATION_IMG;
		loan_apply.setGuaranty_evaluation_path(filePath);
		loan_apply.setWarrant(1);
		
		loan_info.setLoan_name("测试贷款");
		loan_info.setVerify_status('P');
		loan_info.setLoan_period(12);
		loan_info.setAmount(new BigDecimal(100000000));
		loan_info.setAnnual_interest_loan(new BigDecimal(12.50));
		loan_info.setAnnual_interest_invest(new BigDecimal(10.50));
		loan_info.setLoan_apply(loan_apply);
		loan_apply.setLoan_info(loan_info);
		
		loan_id = dao.createLoanWithoutCompany(loan_apply);
		
		//Get the loan:
		Loan_Apply_Info loan_get = null;
		loan_get = dao.getLoanApply(loan_id);
		
		assertEquals(loan_get.getId(),loan_id);
		assertEquals(loan_get.getUser_id(), 1);
		assertEquals(loan_get.getAmount(), new BigDecimal(10000000).setScale(2, BigDecimal.ROUND_HALF_UP));
		assertEquals(loan_get.getLoan_period(), 12);
		assertEquals(loan_get.getInterest(), new BigDecimal(12.50).setScale(2, BigDecimal.ROUND_HALF_UP));
		//assertEquals(loan_get.getRaise_end_time(), date);
		assertEquals(loan_get.getLoan_card(), "62220011012285741");
		assertEquals(loan_get.getLoan_card_bank(), "浙商银行");
		
		//assertEquals(loan_get.getLoan_card_img().getBinaryStream(), new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.LOAN_BANK_CARD_IMG))));
		/*File test = new File("./test.jpg");
		FileOutputStream fos = new FileOutputStream(test);
		OutputStream out = fos;
		InputStream in = loan_get.getLoan_card_img().getBinaryStream();
		byte[] buf = new byte[1024];
		int data;
		while ((data = in.read())!= -1){
			out.write(buf, 0 , data);
		}
		in.close();
		out.close();
		*/
		//assertEquals(loan_get.getAccountant_prove().getBinaryStream(), new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.ACCOUNTANT_PROVE_IMG))));
		//assertEquals(loan_get.getTreasure_prove().getBinaryStream(), new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.TREASURE_PROVE_IMG))));
		//assertEquals(loan_get.getFinancial_report().getBinaryStream(), new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.FINANCIAL_REPORT_IMG))));
		//assertEquals(loan_get.getTax_prove().getBinaryStream(), new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.TAX_PROVE_IMG))));
		assertEquals(loan_get.getLoan_investigation(), "这个项目比较好，公司有较好的收入，现金流稳定。");
		assertEquals(loan_get.getLoan_usage(),"该资金主要用于补充公司经营性现金流");
		//assertEquals(loan_get.getContract_prove().getBinaryStream(), new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.CONTRACT_PROVE_IMG))));
		assertEquals(loan_get.isGuaranty_flag(),true);
		assertEquals(loan_get.getGuaranty_name(),"恒生电子集团");
		//assertEquals(loan_get.getGuaranty_prove().getBinaryStream(), new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.GUARANTY_PROVE_IMG))));
		//assertEquals(loan_get.getGuaranty_evaluation().getBinaryStream(), new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(new File("").getAbsolutePath() + "\\src\\test\\resources\\" + this.GUARANTY_EVALUATION_IMG))));
		assertEquals(loan_get.getWarrant(),1);
		
		assertEquals(loan_get.getLoan_info().getId(),loan_get.getId());
		assertEquals(loan_get.getLoan_info().getLoan_name(),"测试贷款");
		assertEquals(loan_get.getLoan_info().getVerify_status(),'P');
		assertEquals(loan_get.getLoan_info().getLoan_period(),12);
		assertEquals(loan_get.getLoan_info().getAmount(),new BigDecimal(100000000).setScale(2, BigDecimal.ROUND_HALF_UP));
		assertEquals(loan_get.getLoan_info().getAnnual_interest_loan(),new BigDecimal(12.50).setScale(2, BigDecimal.ROUND_HALF_UP));
		assertEquals(loan_get.getLoan_info().getAnnual_interest_invest(),new BigDecimal(10.50).setScale(2, BigDecimal.ROUND_HALF_UP));
				
		//update status:
		boolean b = dao.updateLoanStatus(loan_get.getId(), 'A');
		assertEquals(b, true);
		
		Loan_Apply_Info loan1 = null;
		loan1 = dao.getLoanApply(loan_get.getId());
		assertEquals(loan1.getLoan_info().getVerify_status(), 'A');
		
		//delete the loan:
		dao.deleteLoanApply(loan_get);
		
		//create company:
		company.setCompany_name("中华测试公司");
		company.setCompany_loan_role('W');
		company.setCompany_address("莘松路1000弄3728号");
		company.setCompany_type('P');
		company.setBusiness("业务范围：互联网金融，金融信息咨询。。。");
		company.setReg_capital(new BigDecimal(100000000));
		company.setRepresentive("陈锋");
		company.setEmployee_num(10000);
		company.setCreate_date(new Date());
		company.setUpdate_date(new Date());
		company.setLoan_record(3);
		
		dao.createCompany(company);
		
	}
}
