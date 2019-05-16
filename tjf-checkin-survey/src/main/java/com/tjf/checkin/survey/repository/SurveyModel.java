package com.tjf.checkin.survey.repository;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
@Entity
@Table(name="Survey")
public class SurveyModel implements Serializable {

	private static final long serialVersionUID = -7580115485706153959L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String email;
	
	@NotNull
	private String event;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 5)
	private Integer note;
	
	@Size(max = 400)
	private String description;
	
}

