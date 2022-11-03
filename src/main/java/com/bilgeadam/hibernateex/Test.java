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
		address.setStreet("Bursa Sk.");
		address.setDoorNumber(123);
		
		
		Person person = new Person();
		person.setFirstName("MustafaCan");
		person.setLastName("Ozturk");
		person.setAddress(address);
		
		people.add(person);
		address.setPeople(people);
		
		AddressDao addressDao = new AddressDao();
		PersonDao personDao = new PersonDao();
//		addressDao.create(address);
//		personDao.create(person);
		
//		personDao.delete(7);
//		personDao.update(9,person,5);
		
//		personDao.update(0, person);
		
//		addressDao.create(address);
//		personDao.create(person);
		
//		addressDao.find(6);
//		Address newAddress = new Address();
//		newAddress.setStreet("Sisli");
//		newAddress.setDoorNumber(1000);	
//		addressDao.update(6, newAddress);
		
//		addressDao.delete(8);
		
//		for (Address addressa : addressDao.listAll()) {
//			System.out.println(addressa);
//		} ;
		
//		addressDao.countAddress();

//		addressDao.avgDoorNumber();
//	addressDao.distinctDoorNumber();	
//		addressDao.filterDoorNumber(45);
//		addressDao.betweenMethod(120, 999);
		addressDao.orderAddressByDoorNumber();
	}

}
