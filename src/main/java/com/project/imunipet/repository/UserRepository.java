package com.project.imunipet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.project.imunipet.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByLogin(String login);
}
