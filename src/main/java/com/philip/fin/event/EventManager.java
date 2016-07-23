package com.philip.fin.event;

import org.apache.log4j.Logger;

public class EventManager {
	
	private static EventManager manager = null;
	
	private EventDAO eventDAO = null;
	
	private static final Logger logger = Logger.getLogger(EventManager.class);
	
	public static EventManager getInstance() {
		if (manager == null) manager = new EventManager();
		return manager;
	}
	
	private EventManager() {
		eventDAO = new EventDAO();
	}
	
	public int createEvent(Event event) throws EventException {
		return eventDAO.createEvent(event);
	}
}
