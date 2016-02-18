package com.journaldev.spring.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.repository.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/servlet-context.xml")
public class PersonTest {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void testPerson() {
		System.out.println("--- Find By ID ---");
		Person person = personRepository.findById(4);
		System.out.println("-------------------------------");
		System.out.println(person.getCountry());
		System.out.println(person.getEmailId());
		System.out.println(person.getFirstname());
		System.out.println(person.getLastname());
		System.out.println(person.getId());
	}
	
	@Test
	public void testFindByFirstAndLastName(){
		System.out.println("Find By FirstName and LastName");
		Person person = personRepository.findByFirstnameAndLastname("Prateek", "Ashtikar");
		System.out.println("-------------------------------");
		System.out.println(person.getCountry());
		System.out.println(person.getEmailId());
		System.out.println(person.getFirstname());
		System.out.println(person.getLastname());
		System.out.println(person.getId());
	}
	
	@Test
	public void testInsertData(){
		System.out.println("Insert Data into MySQL DB");
		Person person = new Person();
		person.setCountry("USA");
		person.setEmailId("andrewj@gmail.com");
		person.setFirstname("Andrew");
		person.setLastname("Kerr");
		
		personRepository.save(person);
		
		Person p = personRepository.findOne(person.getId());
		System.out.println(p.toString());
	}
	
	
	@Test
	public void testSelectDSL(){
		String sql = "select p.firstname, p.lastname from Person p where p.firstname = 'Prateek'";
		TypedQuery<Person> query = em.createQuery(sql,Person.class);

		List<Person> posts = query.getResultList();
		for (Person person : posts) {
			System.out.println(person.toString());
		}
	}

}
