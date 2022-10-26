package com.bilgeadam.hibernateex.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.hibernateex.entity.Address;

import jakarta.persistence.TypedQuery;

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
		
		Session session = null;
		try {
			Address updateAddress = find(id);
			updateAddress.setStreet(entity.getStreet());
			updateAddress.setDoorNumber(entity.getDoorNumber());
			
			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateAddress);
			session.getTransaction().commit();
			System.out.println("Successfully updated.");
		} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Some problem occured while UPDATING address data.");
		}finally {
			session.close();
		}	
	}
	@Override
	public void delete(long id) {

		Session session= null;
		
		try {
			Address deleteAddress = find(id);
			if(deleteAddress != null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deleteAddress);
				session.getTransaction().commit();
				
				System.out.println("Successfully deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING address data.");		
		} finally {
			session.close();
		}
	}

	@Override
	public List<Address> listAll() {
		
		Session session = databaseConnection();
		//hibernate query language
		String hql = "select adr from Address as adr";
		
		TypedQuery<Address> typedQuery = session.createQuery(hql,Address.class);
		List<Address> addressList = typedQuery.getResultList();
		
		return addressList;
	}

	@Override
	public Address find(long id) {
		Address address = null;
		Session session = databaseConnection();
		
		try {
			address = session.find(Address.class, id);
			
			if(address != null) {
				System.out.println("Found address : " + address);
			}else {
				System.out.println("Address not found");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while FIND address data.");
		}finally {
			session.close();
		}
		return address;
	}

}
