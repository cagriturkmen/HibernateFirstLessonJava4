package com.bilgeadam.hibernateex;

import java.util.ArrayList;
import java.util.List;

import com.bilgeadam.hibernateex.dao.AddressDao;
import com.bilgeadam.hibernateex.dao.PersonDao;
import com.bilgeadam.hibernateex.entity.Address;
import com.bilgeadam.hibernateex.entity.Person;

public class Test {

	public static void main(String[] args) {
		
		List<Person> people = new ArrayList<>();
		
		Address address = new Address();
		address.setStreet("Sahin Sk.");
		address.setDoorNumber(40);
		
		
		Person person = new Person();
		person.setFirstName("Cagri");
		person.setLastName("Turkmen");
		person.setAddress(address);
		
		people.add(person);
		address.setPeople(people);
		
		AddressDao addressDao = new AddressDao();
		PersonDao personDao = new PersonDao();
		
	//	addressDao.create(address);
	//	personDao.create(person);
		
	//	addressDao.find(6);
//		Address newAddress = new Address();
//		newAddress.setStreet("Sisli");
//		newAddress.setDoorNumber(1000);	
//		addressDao.update(6, newAddress);
		
//		addressDao.delete(8);
		
		for (Address addressa : addressDao.listAll()) {
			System.out.println(addressa);
		} ;
	}

}
