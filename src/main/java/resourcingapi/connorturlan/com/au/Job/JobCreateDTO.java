package resourcingapi.connorturlan.com.au.Job;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class JobCreateDTO {
	@NotBlank
	String name;
	
	@PositiveOrZero
	Long startDate;

	@PositiveOrZero
	Long endDate;

	public JobCreateDTO(String name, Long start, Long end) {
		this.name = name;
		
		startDate = start;
		endDate = end;
	}

	public String getName() {
		return name;
	}

	public Long getStartDate() {
		return startDate;
	}

	public Long getEndDate() {
		return endDate;
	}
}
