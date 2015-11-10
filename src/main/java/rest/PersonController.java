package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import databaseService.DatabaseService;
import entity.Person;

@Controller
@RequestMapping("/accounts")
public class PersonController {
	
	private DatabaseService _service;

	@Autowired
	public PersonController(DatabaseService _service) {
		super();
		this._service = _service;
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public @ResponseBody Person getPerson(@PathVariable String name){
		Person p = _service.findPerson(name);
		if(p != null){
			return p;
		}else{
			return null;
		}
	}

}
