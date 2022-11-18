package resourcingapi.connorturlan.com.au.Temp;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temps")
public class TempController {
	@Autowired
	private TempService tempService;

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
		List<Temp> temps = this.tempService.FindAvailable(jobId);
		return new ResponseEntity<>(temps, temps != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
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
