package com.epam.jmp.springmvc.persontask.controller;

import com.epam.jmp.springmvc.persontask.entity.Person;
import com.epam.jmp.springmvc.persontask.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping("/personList")
	public ModelAndView personList() {
		List<Person> personList = personService.getPersonList();
		return new ModelAndView("personList", "list", personList);
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("person") Person person){
		personService.savePerson(person);
		return new ModelAndView("redirect:/personList");
	}

	@RequestMapping("/addPersonForm")
	public ModelAndView showAddPersonForm(){
		return new ModelAndView("newPersonForm", "command", new Person());
	}

	@RequestMapping(value="/editPerson/{id}")
	public ModelAndView showEditPersonForm(@PathVariable int id){
		Person person = personService.getPersonById(id);
		return new ModelAndView("editPersonForm", "command", person);
	}

	@RequestMapping(value="/update",method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("person") Person person){
		personService.updatePerson(person);
		return new ModelAndView("redirect:/personList");
	}

	@RequestMapping(value="/deletePerson/{id}",method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id){
		personService.deletePerson(id);
		return new ModelAndView("redirect:/personList");
	}
}
