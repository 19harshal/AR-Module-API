package in.ashokit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.ashokit.binding.CitizenApp;
import in.ashokit.entity.CitizenAppEntity;
import in.ashokit.repo.CitizenAppRepo;

@Service
public class ARServiceImpl implements ARService {

	@Autowired
	private CitizenAppRepo appRepo;
	
	@Override
	public Integer createApplication(CitizenApp app) {
		
		String endUrl = "https://ssa-web-api.herokuapp.com/ssn/{ssn}";
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> entity = rt.getForEntity(endUrl, String.class,app.getSsn());
		String state = entity.getBody();
		
		if("New Jersey".equalsIgnoreCase(state)) {
			CitizenAppEntity citiApp = new CitizenAppEntity();
			BeanUtils.copyProperties(app, citiApp);
			citiApp.setState(state);
			
			CitizenAppEntity save = appRepo.save(citiApp);
			return save.getAppID();
		}
		
		
		return 0;
	}

}
