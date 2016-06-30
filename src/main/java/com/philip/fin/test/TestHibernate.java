package com.philip.fin.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.philip.fin.test.User;

public class TestHibernate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure();
		
		//configuration.addResource("User.hbm.xml");
		//configuration.addAnnotatedClass(User.class);
		ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sf = configuration.buildSessionFactory();
		Session ss = sf.openSession();
		
		User user1 = new User();
		user1.setUsername("Robbin");
		user1.setPassword("Hello world from Robbin");
		
		User user2 = new User();
		user2.setUsername("Candy");
		user2.setPassword("Hello world from Candy!");
		
		User user3 = new User();
		user3.setUsername("Nina");
		user3.setPassword("Hello world from Nina!");
		
		//saving objects to session
		ss.beginTransaction();
		ss.save(user1);
		ss.save(user2);
		ss.save(user3);
		ss.getTransaction().commit();
		ss.close();
		sf.close();
	}

}
