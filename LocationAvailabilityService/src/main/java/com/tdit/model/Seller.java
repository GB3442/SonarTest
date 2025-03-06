package com.tdit.model;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "sellers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sellerId;

	@NotBlank(message = "Name cannot be empty or null")
	@Size(max = 100, message = "Name should not exceed 100 characters")
	private String name;
	@Size(max = 500, message = "Description should not exceed 500 characters")
	private String description;
	@NotNull(message = "Active status cannot be null")
	private boolean isActive;
	@NotNull(message = "Verification status cannot be null")
	private boolean isVerified;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "seller", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Location> availableLocations;

	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Product> productList;
}
