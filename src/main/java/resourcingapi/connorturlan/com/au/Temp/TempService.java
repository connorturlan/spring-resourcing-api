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

	public Optional<Temp> FindOne(long id) {
		return repository.findById(id);
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
		Optional<Job> maybeJob = jobService.FindOne(jobId);
		if (maybeJob.isEmpty()) { return null; }
		Job job = maybeJob.get();
		LocalDate startDate = job.getStartDate();
		LocalDate endDate = job.getEndDate();
		
		return repository.FindAvailable(startDate, endDate);
	}
}
