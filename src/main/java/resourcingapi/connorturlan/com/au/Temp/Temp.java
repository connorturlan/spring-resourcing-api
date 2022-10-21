package resourcingapi.connorturlan.com.au.Temp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import resourcingapi.connorturlan.com.au.Job.Job;

@Entity
public class Temp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column
	String firstName;

	@Column
	String lastName;

	@OneToMany(mappedBy = "temp")
	private List<Job> jobs;

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Temp(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Temp() {
		firstName = "John";
		lastName = "Smith";
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
