package com.cognizant.RetailBanking.Customers.model;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER")
@Entity
public class CustomerEntity {

	@Id
	@Column(name="userid" , unique=true)
	@NotBlank
	private String userid;
	
	@Column(name="username")
	@NotBlank
	private String username;
	
	@Column(name="password")
	@NotBlank
	private String password;
	
	@Column(name="dateOfBirth")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateOfBirth;
	
	@Column(name="pan_no", length=10)
	@NotBlank
	private String pan_no;
	
	@Column(name="address")
	@NotBlank
	private String address;
	
	/*
	@Transient
	private List<Account> accounts = new ArrayList<>();
	*/
}
