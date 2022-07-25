package com.cognizant.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.authentication.model.AppUser;

/**
 * User Repository Interface to handle user data
 */

@Repository
public interface UserRepository extends JpaRepository<AppUser, String> {

}