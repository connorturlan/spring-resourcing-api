package resourcingapi.connorturlan.com.au.Temp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TempRepository extends JpaRepository<Temp, Long> {
	// @Query("SELECT j.temp FROM Job j where j.temp IS NOT NULL")
	@Query("SELECT j.temp FROM Job j WHERE NOT ((?1 <= j.startDate AND ?1 >= j.endDate) OR (?2 <= j.startDate AND ?2 >= j.endDate))")
  	List<Temp> FindAvailable(LocalDate jobStart, LocalDate jobEnd);
}
