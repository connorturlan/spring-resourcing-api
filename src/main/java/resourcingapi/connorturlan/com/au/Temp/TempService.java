package resourcingapi.connorturlan.com.au.Temp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import resourcingapi.connorturlan.com.au.Job.Job;
import resourcingapi.connorturlan.com.au.Job.JobService;

@Service
@Transactional
public class TempService {
	@Autowired
	private TempRepository repository;

	@Autowired
	private JobService jobService;

	public List<Temp> FindAll() {
		return repository.findAll();
	}

	public Temp FindOne(long id) {
		// try and get the requested job.
		Optional<Temp> maybeTemp = repository.findById(id);

		// return the found temp or null depending on if the optional is filled.
		return maybeTemp.isEmpty() ? null : maybeTemp.get();
	}

	public Temp Create(TempCreateDTO data) {
		// format the data to be meaningful.
		String first = data.getFirstName().trim();
		String last = data.getLastName().trim();

		// create the new Temp object, and send it to the DB.
		Temp temp = new Temp(first, last);
		repository.save(temp);
		return temp;
	}

	public List<Temp> FindAvailable(long jobId) {
		Job job = jobService.FindOne(jobId);
		if (job == null) { return null; }
		LocalDate startDate = job.getStartDate();
		LocalDate endDate = job.getEndDate();
		
		return repository.FindAvailable(startDate, endDate);
	}
}
