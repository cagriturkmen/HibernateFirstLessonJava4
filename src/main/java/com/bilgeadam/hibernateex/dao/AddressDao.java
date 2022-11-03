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
	
	public void countAddress() {
		
		Session session = databaseConnection();

		String hql = "select count(*) from Address";
		
		TypedQuery<Long> typedQuery = session.createQuery(hql, Long.class);
		Long addressCount = typedQuery.getSingleResult();
		
		System.out.println(addressCount);		
	}
	
	public void avgDoorNumber() {
		
		Session session = databaseConnection();
		/*
		 -sum
		 -avg
		 -count
		 -min
		 -max
		 */
		String hql = "select avg(doorNumber) from Address";
		TypedQuery<Double> typedQuery = session.createQuery(hql, Double.class);
		Double avgDoorNum = typedQuery.getSingleResult();
		
		System.out.println(avgDoorNum);		
	}
	
	public void distinctDoorNumber() {
		
		Session session = databaseConnection();
		String hql = "select distinct(doorNumber) from Address";
		TypedQuery<Integer> typedQuery = session.createQuery(hql, Integer.class);
		List<Integer> doorNumberList = typedQuery.getResultList();
		
		for (Integer integer : doorNumberList) {
			System.out.println(integer);
		}
		
	}
	
	public void filterDoorNumber(int input) {
		
		Session session = databaseConnection();
		String hql = "select adr from Address as adr where doorNumber<=:key";
		
		TypedQuery<Address> typedQuery = session.createQuery(hql, Address.class);
		typedQuery.setParameter("key", input);
		
		System.out.println(typedQuery.getResultList());
		
	}
	
	public void betweenMethod(int a, int b) {
		
		Session session = databaseConnection();
		String hql = "select address from Address as address where doorNumber between :firstKey and :secondKey ";
		TypedQuery<Address> typedQuery = session.createQuery(hql, Address.class);
		typedQuery.setParameter("firstKey", a);
		typedQuery.setParameter("secondKey", b);
		List<Address> adddresList = typedQuery.getResultList();
		System.out.println(adddresList);
	}
	
	public void orderAddressByDoorNumber() {
		
		Session session = databaseConnection();
		String hql = "select address from Address as address order by address.doorNumber desc";
		TypedQuery<Address> typedQuery = session.createQuery(hql, Address.class);
		
		for (Address address : typedQuery.getResultList()) {
			System.out.println(address);
		}
		
		
	}
	
	

}
