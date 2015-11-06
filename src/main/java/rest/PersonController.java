package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import databaseService.DerbyDatabaseService;

@Controller
@RequestMapping("/accounts")
public class PersonController {
	
	private DerbyDatabaseService _service;

	@Autowired
	public PersonController(DerbyDatabaseService _service) {
		super();
		this._service = _service;
	}
	
	

}
