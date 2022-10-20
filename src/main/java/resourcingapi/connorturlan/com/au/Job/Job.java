package resourcingapi.connorturlan.com.au.Job;

import javax.persistence.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import resourcingapi.connorturlan.com.au.Temp.Temp;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@Column
	String name;

	@Column
	Date startDate;

	@Column
	Date endDate;

	public Job() {
		name = "john smith";
		startDate = new Date();
		endDate = new Date();
	}
}
