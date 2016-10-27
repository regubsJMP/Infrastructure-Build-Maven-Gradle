package com.epam.jmp.springmvc.persontask.service;

import com.epam.jmp.springmvc.persontask.dao.PersonDAO;
import com.epam.jmp.springmvc.persontask.entity.Person;
import com.epam.jmp.springmvc.persontask.exceptions.ConnectionPoolException;
import com.epam.jmp.springmvc.persontask.exceptions.DAOException;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
	private PersonDAO personDAO;

	public List<Person> getPersonList() {
		List<Person> persons = new ArrayList<Person>();
		try {
			return personDAO.readPersonsList();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
		return persons;
	}

	public void savePerson(Person person) {
		try {
			personDAO.savePerson(person);
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public Person getPersonById(int id) {
		Person person = null;
		try {
			person = personDAO.getPersonById(id);
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return person;
	}

	public void updatePerson(Person person) {
		try {
			personDAO.updatePerson(person);
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void deletePerson(int id) {
		try {
			personDAO.deletePerson(id);
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}


	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
}
