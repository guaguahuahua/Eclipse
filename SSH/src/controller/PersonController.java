package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Person;
import service.PersonService;

@Controller
//@RequestMapping("/hello")
public class PersonController {

//	@Autowired private PersonService personService;
	
	@RequestMapping("/mvc")
//	@ResponseBody
	public String helloSpring() {
		System.out.println("come here!");
		return "hello";
	}

//	@RequestMapping(value="/selectPersons")
//	@ResponseBody
//	public List<Person> selectPersons(){
//		return personService.selectPersons();
//	}
//	
//	@RequestMapping(value="/addPerson")
//	public void addPerson(Person p) {
//		personService.addPerson(p);
//	}
//	
//	@RequestMapping(value="/delPersonById")
//	public void delPersonById(String id) {
//		personService.delPersonById(id);
//	}
//	
//	@RequestMapping(value="/updatePerson")
//	public void updatePerson(Person p) {
//		personService.updatePerson(p);
//	}
//	
//	@RequestMapping(value="/queryPersonById")
//	public Person queryPersonById(String id) {
//		return personService.queryPersonById(id);
//	}
}