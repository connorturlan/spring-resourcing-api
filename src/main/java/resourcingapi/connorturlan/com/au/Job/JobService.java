package resourcingapi.connorturlan.com.au.Job;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class JobService {
	@Autowired
	private JobRepository repository;

	public List<Job> FindAll() {
		return repository.findAll();
	}

	public Optional<Job> FindOne(long id) {
		return repository.findById(id);
	}

	public Job Create(JobCreateDTO data) {
		String name = data.getName().trim();
		LocalDate start = data.getStartDate();
		LocalDate end = data.getEndDate();

		start = (start != null) ? start : LocalDate.now();
		end = (end != null) ? end : LocalDate.now();

		Job job = new Job(name, start, end);
		repository.save(job);
		return job;
	}

	public Job Update(Job job) {
		repository.save(job);
		return job;
	}
}
