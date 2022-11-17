package resourcingapi.connorturlan.com.au.Temp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TempRepository extends JpaRepository<Temp, Long> {
	// @Query("SELECT j.temp FROM Job j where j.temp IS NOT NULL")
	@Query("SELECT j.temp FROM Job j WHERE ?1 BETWEEN j.startDate AND j.endDate AND ?2 BETWEEN j.startDate AND j.endDate")
  	List<Temp> FindAvailable(LocalDate jobStart, LocalDate jobEnd);
}
