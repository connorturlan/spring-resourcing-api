package resourcingapi.connorturlan.com.au.Job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {
	@Query("SELECT j FROM Job j WHERE j.temp IS NOT null")
  	List<Job> FindAssigned();

	@Query("SELECT j FROM Job j WHERE j.temp IS null")
	List<Job> FindUnassigned();
}