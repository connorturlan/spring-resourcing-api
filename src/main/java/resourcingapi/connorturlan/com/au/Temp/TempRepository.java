package resourcingapi.connorturlan.com.au.Temp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TempRepository extends JpaRepository<Temp, Long> {
	@Query("select t from Temp t left join Job j on t.id = j.temp.id WHERE j IS NULL OR NOT ((?1 <= j.startDate AND ?1 >= j.endDate) OR (?2 <= j.startDate AND ?2 >= j.endDate))")
	List<Temp> FindAvailable(LocalDate jobStart, LocalDate jobEnd);
}
