package resourcingapi.connorturlan.com.au.Job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
	@Autowired
	private JobService service;

	@GetMapping
	public ResponseEntity<List<Job>> ReadAll() {
		List<Job> jobs = service.FindAll();
		return new ResponseEntity<>(jobs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<Job>> ReadOne() {
		List<Job> jobs = service.FindAll();
		return new ResponseEntity<>(jobs, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<List<Job>> Create() {
		List<Job> jobs = service.FindAll();
		return new ResponseEntity<>(jobs, HttpStatus.OK);
	}
}
