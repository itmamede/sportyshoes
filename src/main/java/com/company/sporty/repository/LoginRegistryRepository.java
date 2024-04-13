package com.company.sporty.repository;

import com.company.sporty.entity.Login;
import com.company.sporty.entity.LoginRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRegistryRepository extends JpaRepository<LoginRegistry, Integer>{}
