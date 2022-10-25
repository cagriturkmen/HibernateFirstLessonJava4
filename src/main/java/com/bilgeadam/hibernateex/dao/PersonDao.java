package com.bilgeadam.hibernateex.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.hibernateex.entity.Address;
import com.bilgeadam.hibernateex.entity.Person;

public class PersonDao implements IRepository<Person>  {

	@Override
	public void create(Person entity) {
		Session session = null;
		
		try {			
			session = databaseConnection();
			session.getTransaction().begin();
			System.out.println(session);
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Person data is added to DB");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while adding Person data.");
		} finally {
			session.close();
		}
		
		
	}

	@Override
	public void update(long id, Person entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Person> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
