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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temps")
public class TempController {
	@Autowired
	private TempService service;

	@GetMapping
	public ResponseEntity<List<Temp>>ReadAll() {
		List<Temp> temps = service.FindAll();
		return new ResponseEntity<>(temps, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Temp> ReadOne(@PathVariable long id) {
		// try and get the requested job.
		Optional<Temp> maybeJob = service.FindOne(id);

		// return an error if the job id isn't found.
		if (maybeJob.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		// otherwise return the requested job.
		return new ResponseEntity<>(maybeJob.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Temp>Create(@Valid @RequestBody TempCreateDTO data) {
		Temp temp = service.Create(data);
		return new ResponseEntity<Temp>(temp, HttpStatus.CREATED);
	}
}
