package resourcingapi.connorturlan.com.au.Job;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
	@GetMapping
	public ResponseEntity<ArrayList<Job>> GetAll() {
		return null;
	}
}
