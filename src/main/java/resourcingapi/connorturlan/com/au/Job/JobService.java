package resourcingapi.connorturlan.com.au.Job;

import java.util.Date;
import java.util.List;

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

	public Job Create(JobCreateDTO data) {
		String name = data.getName().trim();
		Long startDate = data.getStartDate();
		Long endDate = data.getEndDate();

		Job job = new Job(name, startDate, endDate);
		repository.save(job);
		return job;
	}
}
