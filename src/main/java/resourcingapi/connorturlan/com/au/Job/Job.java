package resourcingapi.connorturlan.com.au.Job;

import javax.persistence.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import resourcingapi.connorturlan.com.au.Temp.Temp;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@Column
	@NotBlank
	String name;

	@Column
	@FutureOrPresent
	LocalDate startDate;

	@Column
	@FutureOrPresent
	LocalDate endDate;

	@ManyToOne
	@JoinColumn(name = "temp_id")
	@JsonIgnoreProperties({"jobs"})
	private Temp temp;

	
	public Job(String name, LocalDate startDate, LocalDate endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Job() {
		this.name = "some job";
		this.startDate = LocalDate.now();
		this.endDate = LocalDate.now();
	}

	public long getId() {
		return id;
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

	public Temp getTemp() {
		return temp;
	}
	
	public void setTemp(Temp temp) {
		this.temp = temp;
	}
}
