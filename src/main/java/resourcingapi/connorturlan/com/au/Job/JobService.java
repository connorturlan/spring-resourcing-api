package resourcingapi.connorturlan.com.au.Job;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class JobService {
	@Autowired
	private JobRepository repository;

	public List<Job> GetAll() {
		return repository.findAll();
	}
}
