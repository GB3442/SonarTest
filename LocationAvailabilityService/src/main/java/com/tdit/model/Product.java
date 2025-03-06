package com.tdit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "seller")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;

	private String name;
	private String description;

	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	@JsonBackReference
	private Seller seller;
}
