package resourcingapi.connorturlan.com.au.Temp;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TempService {
	@Autowired
	private TempRepository repository;

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
		return repository.FindAvailable(jobId);
	}
}
