package ag.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table
@Data
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Pattern(regexp = "^\\d{3}\\D{1}$", message = "Enter employee id") // ^[0-9]{3}[a-z]{1}$
	@NotEmpty
	@Column(unique = true)
	@JsonProperty("bows-id")
	private String bowsEmployeeId; 
	
	@NotEmpty(message = "Enter name")
	private String name; 
	
	@Email(message = "Please provide valid email")
	@Column(unique = true)
	private String email; 
	
	@Pattern(regexp = "^07[\\d]{9}$", message = "Enter valid mobile number")
	private String mobile; 
	
	@Pattern(regexp = "^[\\d]{4}$", message = "Enter 4 digit pin")
	private String pin; 
	
	/*
	 * MapsId the id property serves as both Primary Key and Foreign Key. 
	 * You’ll notice that the @Id column no longer uses a @GeneratedValue 
	 * annotation since the identifier is populated with the 
	 * identifier of the post association.
	 */
	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
	@MapsId
	@JsonIgnore
	private EmployeeCard employeeCard; // card is the parent entity

	public Employee() {}

	public Employee(@Pattern(regexp = "^\\d{3}\\D{1}$", message = "Enter employee id") @NotBlank String bowsEmployeeId,
			@NotEmpty(message = "Enter name") String name, 
			@Email(message = "Please provide valid email") String email,
			@Pattern(regexp = "^07[\\d]{9}$", message = "Enter valid mobile number") String mobile,
			@Pattern(regexp = "^[\\d]{4}$", message = "Enter 4 digit pin") String pin) {
		super();
		this.bowsEmployeeId = bowsEmployeeId;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.pin = pin;
	}

	public void setEmployeeCard(EmployeeCard employeeCard) {
		if (employeeCard == null) {
			if(this.employeeCard != null) {
				this.employeeCard.setEmployee(null);
			}
		}
		else {
			employeeCard.setEmployee(this);
		}
		this.employeeCard = employeeCard;
	}

	
}
