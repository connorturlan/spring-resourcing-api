package resourcingapi.connorturlan.com.au.Job;

import javax.persistence.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@Column
	String name;

	@Column
	Long startDate;

	@Column
	Long endDate;

	public Job(String name, Long startDate, Long endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Job() {
		this.name = "some job";
		this.startDate = 0L;
		this.endDate = 0L;
	}

	public long getId() {
		return id;
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
