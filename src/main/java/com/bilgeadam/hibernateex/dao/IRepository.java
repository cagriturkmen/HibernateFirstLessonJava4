package com.bilgeadam.hibernateex.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.hibernateex.utils.HibernateSession;

public interface IRepository<T> {

	public void create(T entity);
	
	public void update(long id, T entity);
	
	public void delete(long id);
	
	ArrayList<T> listAll();
	
	T find(long id);
	
	default Session databaseConnection() {
		return HibernateSession.getSessionFactory().openSession();
	}
	
}
