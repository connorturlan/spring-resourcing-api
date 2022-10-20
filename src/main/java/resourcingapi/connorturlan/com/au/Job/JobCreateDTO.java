package resourcingapi.connorturlan.com.au.Job;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class JobCreateDTO {
	@NotBlank
	String name;
	
	LocalDate startDate;

	LocalDate endDate;

	public JobCreateDTO(String name, LocalDate start, LocalDate end) {
		this.name = name;
		
		startDate = start;
		endDate = end;
	}

	public String getName() {
		return name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}
}
