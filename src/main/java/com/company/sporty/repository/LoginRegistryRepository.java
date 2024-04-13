package com.company.sporty.repository;

import com.company.sporty.entity.LoginRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRegistryRepository extends JpaRepository<LoginRegistry, Integer>{}
