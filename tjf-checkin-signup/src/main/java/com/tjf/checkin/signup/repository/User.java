package com.tjf.checkin.signup.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "{User.name.NotBlank}")
	private String name;
	
	@NotBlank(message = "{User.email.NotBlank}")
    private String email;

    @NotBlank(message = "{User.provider.NotBlank}")
    private String provider;

    @Column(unique = true)
    @NotBlank(message = "{User.macAdress.NotBlank}")
    private String macAdress;
}