package ag.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "card")
@Data // auto generates getters, setters, equals and hash code 
public class EmployeeCard extends RepresentationModel<EmployeeCard> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Lob // to persist a large object into db
	private Employee employee; // employee is the child entity 
	
	@Pattern(regexp = "^[\\d\\D]{16}$", message = "Enter 16 alphanumeric characters card number")
	@JsonProperty("card-number")
	@NotEmpty
	private String dataCard; 
	
	private Double balance;
	
	@JsonIgnore
	private Boolean active; 
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@JsonIgnore
	private Date createdAt = new Date(); 
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@JsonIgnore
	private Date modifiedAt = new Date();

	protected EmployeeCard() {}

	public EmployeeCard(
			@Pattern(regexp = "^[\\d\\D]{16}$", message = "Enter 16 alphanumeric characters card number") @NotNull String dataCard,
			Double balance) {
		super();
		this.dataCard = dataCard;
		this.balance = balance;
	}

	public Double topupBalance(Double topupAmount) {
		if(topupAmount > 0.00) {
			balance += topupAmount; 
			return balance; 
		}
		return balance; 
	}

	
	/*
	* hashCode()is a unique hash/number attached to every object whenever the object is created.
	* 
	* equals()method is used to compare two objects based on their properties.
	* 
	* So whenever two objects are compared. Their hash code and properties are compared. 
	* If both (hash code & properties value) are same then the object is considered equal otherwise not equal.
	* Therefore, it is very important to override hascode() and equals() method of an object.
	*/
	
}
