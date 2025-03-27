package com.demo.ethtracker.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ethtracker.entity.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
} 
