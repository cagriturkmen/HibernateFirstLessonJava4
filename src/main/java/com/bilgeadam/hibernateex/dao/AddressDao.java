package com.bilgeadam.hibernateex.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.hibernateex.entity.Address;

public class AddressDao implements IRepository<Address>  {

	@Override
	public void create(Address entity) {
		Session session = null;
		
		try {			
			session = databaseConnection();
			session.getTransaction().begin();
			System.out.println(session);
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Address data is added to DB");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while adding address data.");
		} finally {
			session.close();
		}
		
		
	}

	@Override
	public void update(long id, Address entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Address> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
