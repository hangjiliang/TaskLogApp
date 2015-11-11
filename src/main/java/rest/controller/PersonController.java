package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import databaseService.DatabaseService;
import entity.Person;

@Controller
public class PersonController {
	
	DatabaseService service;
	
	public DatabaseService getService() {
		return service;
	}
	
	@Autowired
	public void setService(DatabaseService service) {
		this.service = service;
	}

	@RequestMapping(value = ("/person"), method = RequestMethod.GET)
	@ResponseBody
	public Person getPerson(){
		System.out.print("in controller");
		Person p = new Person();
		p.setName("Jiliang");
		p.setPassword("12345test");
		return p;
	}

}
