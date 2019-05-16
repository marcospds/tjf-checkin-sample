package com.tjf.checkin.signup.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="userCheckin")
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "{User.name.NotBlank}")
	private String name;
	
	@NotBlank(message = "{User.email.NotBlank}")
	private String email;

    @NotBlank(message = "{User.provider.NotBlank}")
    private String provider;

    //@Column(unique = true)
    @NotBlank(message = "{User.macAdress.NotBlank}")
    private String macAdress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public long getId() {
        return id;
    }
    
    
}