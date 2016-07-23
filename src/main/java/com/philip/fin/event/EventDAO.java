package com.philip.fin.event;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.philip.fin.basic.HibernateUtil;

public class EventDAO {

	private static final Logger logger = Logger.getLogger(EventDAO.class);
	
	private Configuration configuration = null;
	private ServiceRegistry sr = null;
	private SessionFactory sf =null;
	private Session ss = null;
	
	public int createEvent(Event event) throws EventException {
		logger.debug("begin to create event");
		int event_id;
		
		HibernateUtil util = HibernateUtil.getInstance();
		ss = util.getSessionFactory().openSession();
		
		try {
			ss.beginTransaction();
			ss.save(event);
			event_id = event.getId();
			ss.getTransaction().commit();
		} catch (HibernateException e) {
			logger.error(e);
			e.printStackTrace();
			throw new EventException(e);
		} finally {
			ss.close();
		}
		
		return event_id;
	}
}
