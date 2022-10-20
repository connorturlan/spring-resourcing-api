package resourcingapi.connorturlan.com.au.Temp;

import java.util.List;

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

	public Temp Create(TempCreateDTO data) {
		return null;
	}
}
