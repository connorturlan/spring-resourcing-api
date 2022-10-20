package resourcingapi.connorturlan.com.au.Temp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	public Temp() {
		firstName = "John";
		lastName = "Smith";
	}
}
