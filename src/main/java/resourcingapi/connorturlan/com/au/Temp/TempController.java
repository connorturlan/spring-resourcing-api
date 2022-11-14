package resourcingapi.connorturlan.com.au.Temp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import resourcingapi.connorturlan.com.au.Job.Job;
import resourcingapi.connorturlan.com.au.Job.JobService;

import resourcingapi.connorturlan.com.au.DateUtils.DateUtils;

@RestController
@RequestMapping("/temps")
public class TempController {
	@Autowired
	private TempService tempService;

	@Autowired
	private JobService jobService;

	@GetMapping
	public ResponseEntity<List<Temp>> HandleGet(@RequestParam(required = false) Long jobId) {
		if (jobId != null) {
			return GetAvailableTemps(jobId);
		}
		return ReadAll();
	}

	public ResponseEntity<List<Temp>> ReadAll() {
		List<Temp> temps = tempService.FindAll();
		return new ResponseEntity<>(temps, HttpStatus.OK);
	}

	public ResponseEntity<List<Temp>> GetAvailableTemps(long jobId) {
		return new ResponseEntity<>(this.tempService.FindAvailable(jobId), HttpStatus.OK);

		// get the date range for the specified job.
		// Optional<Job> maybeJob = jobService.FindOne(jobId);
		// if (maybeJob.isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
		// Job job = maybeJob.get();
		// LocalDate startDate = job.getStartDate();
		// LocalDate endDate = job.getEndDate();

		// // get all temps, and filter out the temps with jobs within the date range.
		// List<Temp> allTemps = tempService.FindAll();
		// List<Temp> temps = allTemps.stream().filter(
		// 	temp -> temp.getJobs().stream().filter(
		// 		tempJob -> (
		// 					DateUtils.DateWithinRange(startDate, endDate, tempJob.getStartDate()) 
		// 					|| DateUtils.DateWithinRange(startDate, endDate, tempJob.getEndDate())
		// 				)
		// 			)
		// 		.collect(Collectors.toList()).size() <= 0
		// 		)
		// 	.collect(Collectors.toList());

		// return new ResponseEntity<>(temps, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Temp> ReadOne(@PathVariable long id) {
		// try and get the requested job.
		Optional<Temp> maybeJob = tempService.FindOne(id);

		// return an error if the job id isn't found.
		if (maybeJob.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		// otherwise return the requested job.
		return new ResponseEntity<>(maybeJob.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Temp>Create(@Valid @RequestBody TempCreateDTO data) {
		Temp temp = tempService.Create(data);
		return new ResponseEntity<Temp>(temp, HttpStatus.CREATED);
	}
}
