package resourcingapi.connorturlan.com.au.Temp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TempRepository extends JpaRepository<Temp, Long> {
	@Query()
  	List<Temp> findAllUnassigned();
}
