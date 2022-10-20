package resourcingapi.connorturlan.com.au.Job;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Job> ReadOne(@PathVariable long id) {
		// try and get the requested job.
		Optional<Job> maybeJob = service.FindOne(id);

		// return an error if the job id isn't found.
		if (maybeJob.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		// otherwise return the requested job.
		return new ResponseEntity<>(maybeJob.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Job> Create(@Valid @RequestBody JobCreateDTO data) {
		Job job = service.Create(data);
		return new ResponseEntity<>(job, HttpStatus.CREATED);
	}
}
