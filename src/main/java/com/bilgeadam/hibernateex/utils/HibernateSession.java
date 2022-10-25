package com.bilgeadam.hibernateex.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.hibernateex.entity.Address;
import com.bilgeadam.hibernateex.entity.Person;

public class HibernateSession {
	
	private static SessionFactory sessionFactory;
	
	
	public static SessionFactory getSessionFactory() {	
		
		Configuration conf = new Configuration();
		
		conf.addAnnotatedClass(Person.class);
		conf.addAnnotatedClass(Address.class);
		
		sessionFactory = conf.configure("hibernate.cfg.xml").buildSessionFactory();
		System.out.println("Connection to PostgreSQL via Hibernate is successful.");
		
		return sessionFactory;
	}
	
	
}
