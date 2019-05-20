package com.tjf.checkin.signup.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participant")
public class ParticipantModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "{ParticipantModel.email.NotBlank}")
    @Email(message = "{ParticipantModel.email.EmailBadFormed}")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "{ParticipantModel.macAddress.NotBlank}")
    @Column(unique = true)
    private String macAddress;

    @NotBlank(message = "{ParticipantModel.name.NotBlank}")
    private String name;

    @NotBlank(message = "{ParticipantModel.provider.NotBlank}")
    private String provider;
}