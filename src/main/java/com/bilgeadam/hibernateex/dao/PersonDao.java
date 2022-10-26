package com.bilgeadam.hibernateex.dao;

import java.util.List;
import org.hibernate.Session;
import com.bilgeadam.hibernateex.entity.Person;
import jakarta.persistence.TypedQuery;

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

		Session session = null;
		try {
			Person updatePerson = find(id);
			updatePerson.setFirstName(entity.getFirstName());
			updatePerson.setLastName(entity.getLastName());
			updatePerson.setAddress(entity.getAddress());
			
			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updatePerson);
			session.getTransaction().commit();
			System.out.println("Successfully updated.");
		} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Some problem occured while UPDATING person data.");
		}finally {
			session.close();
		}	
	}

	@Override
	public void delete(long id) {
		Session session= null;
		
		try {
			Person deletePerson = find(id);
			if(deletePerson != null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deletePerson);
				session.getTransaction().commit();
				
				System.out.println("Successfully deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING person data.");		
		} finally {
			session.close();
		}		
	}

	@Override
	public List<Person> listAll() {
		
		Session session = databaseConnection();
		//hibernate query language
		String hql = "select p from Person as p";
		
		TypedQuery<Person> typedQuery = session.createQuery(hql,Person.class);
		List<Person> personList = typedQuery.getResultList();
		
		return personList;
	}

	@Override
	public Person find(long id) {
		Person person = null;
		Session session = databaseConnection();
		
		try {
			person = session.find(Person.class, id);
			
			if(person != null) {
				System.out.println("Found Person : " + person);
			}else {
				System.out.println("Person not found");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while FIND Person data.");
		}finally {
			session.close();
		}
		return person;
	}

}
