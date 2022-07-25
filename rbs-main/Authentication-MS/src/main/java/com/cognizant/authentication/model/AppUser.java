package com.cognizant.authentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Appuser pojo class with getter, setter methods and constructor
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appuser")
public class AppUser {

	@Id
	@Column(name = "userid", length = 20)
	@NotNull
	private String userid;

	@Column(name = "username", length = 20)
	private String username;

	@Column(name = "password")
	private String password;

	private String authToken;

	private String role;
}