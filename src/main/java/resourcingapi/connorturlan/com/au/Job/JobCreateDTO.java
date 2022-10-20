package resourcingapi.connorturlan.com.au.Job;

import javax.validation.constraints.NotBlank;

public class JobCreateDTO {
	@NotBlank
	String name;
	
	Long startDate;
	Long endDate;

	public JobCreateDTO(String name, Long start, Long end) {
		this.name = name;
		
		startDate = start;
		endDate = end;
	}
}
