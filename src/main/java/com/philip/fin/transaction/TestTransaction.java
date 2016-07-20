package com.philip.fin.transaction;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.philip.fin.invest.InvestDAO;

public class TestTransaction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Logger logger = Logger.getLogger(TestTransaction.class);
		
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			scheduler.start();
			
			logger.debug("Hello");
			
			scheduler.shutdown();
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
