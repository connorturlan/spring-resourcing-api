package resourcingapi.connorturlan.com.au.Temp;

import javax.validation.constraints.NotBlank;

public class TempCreateDTO {
	@NotBlank
	String firstName;
	@NotBlank
	String lastName;

	public TempCreateDTO(@NotBlank String firstName, @NotBlank String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
