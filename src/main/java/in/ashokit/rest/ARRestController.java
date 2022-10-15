package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.CitizenApp;
import in.ashokit.service.ARService;

@RestController
public class ARRestController {

	@Autowired
	private ARService service;
	
	@PostMapping("/app")
	public ResponseEntity<String> createCitizenApp(@RequestBody CitizenApp app){
		
		Integer status = service.createApplication(app);
		
		if(status>0) {
			return new ResponseEntity<>("App Id : "+ status, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Invalid SSN", HttpStatus.BAD_REQUEST); 
		}
		
	}
	
}
