package resourcingapi.connorturlan.com.au.Job;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import resourcingapi.connorturlan.com.au.Temp.Temp;
import resourcingapi.connorturlan.com.au.Temp.TempService;

import resourcingapi.connorturlan.com.au.DateUtils.DateUtils;

@RestController
@RequestMapping("/jobs")
public class JobController {
	@Autowired
	private JobService jobService;
	
	@Autowired
	private TempService tempService;

	@GetMapping
	public ResponseEntity<List<Job>> HandleGet(@RequestParam(required = false) Boolean assigned) {
		// route the request based upon whether or not there is a url param specified.
		if (assigned != null) {
			return FindFilterAssigned(assigned);
		}
		return FindAll();
	}
	
	public ResponseEntity<List<Job>> FindAll() {
		List<Job> jobs = jobService.FindAll();
		return new ResponseEntity<>(jobs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> FindOne(@PathVariable long id) {
		// try and get the requested job.
		Optional<Job> maybeJob = jobService.FindOne(id);

		// return an error if the job id isn't found.
		if (maybeJob.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		// otherwise return the requested job.
		return new ResponseEntity<>(maybeJob.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Job> CreateJob(@Valid @RequestBody JobCreateDTO data) {
		// validate the start date is before the end date.
		if (data.getStartDate().compareTo(data.getEndDate()) > 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Job job = jobService.Create(data);

		return new ResponseEntity<>(job, HttpStatus.CREATED);
	}

	@PatchMapping("/{id}/{temp_id}")
	public ResponseEntity<Job> AssignJob(@PathVariable long id, @PathVariable long temp_id) {
		// try and get the requested job.
		Optional<Job> maybeJob = jobService.FindOne(id);

		// return an error if the job id isn't found.
		if (maybeJob.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		// try and get the requested temp.
		Optional<Temp> maybeTemp = tempService.FindOne(temp_id);

		// return an error if the job id isn't found.
		if (maybeTemp.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Job job = maybeJob.get();
		Temp temp = maybeTemp.get();

		// check that the temp does not have a job that overlaps with this time frame.
		for (Job tempJob : temp.getJobs()) {
			if (DateUtils.DatesOverlap(tempJob.getStartDate(), tempJob.getEndDate(), job.getStartDate(), job.getEndDate())) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

		// add the job link.
		job.setTemp(temp);
		jobService.Update(job);

		// otherwise return the requested job.
		return new ResponseEntity<>(maybeJob.get(), HttpStatus.CREATED);
	}

	public ResponseEntity<List<Job>> FindFilterAssigned(boolean assigned) {		
		List<Job> allJobs = jobService.FindAll();
		List<Job> jobs = allJobs.stream().filter(job -> (job.getTemp() != null) == assigned).collect(Collectors.toList());

		return new ResponseEntity<>(jobs, HttpStatus.OK);
	}
}
