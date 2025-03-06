package com.tdit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Country cannot be empty or null")
	@Size(max = 100, message = "Country name should not exceed 100 characters")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Country name must contain only letters and spaces")
	private String country;
	@NotBlank(message = "State cannot be empty or null")
	@Size(max = 100, message = "State name should not exceed 100 characters")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "State name must contain only letters and spaces")
	private String state;
	@NotBlank(message = "District cannot be empty or null")
	@Size(max = 100, message = "District name should not exceed 100 characters")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "District name must contain only letters and spaces")
	private String district;
	@NotBlank(message = "Pincode cannot be empty or null")
	@Pattern(regexp = "^[0-9]{5,10}$", message = "Pincode must be between 5 to 10 digits")
	private String pincode;

	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	@JsonBackReference
	private Seller seller;

    public Location(int i, String country, Seller seller) {
		this.seller=seller;
		this.country=country;
    }
}
