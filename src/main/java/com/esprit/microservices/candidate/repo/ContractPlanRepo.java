package com.esprit.microservices.candidate.repo;

import com.esprit.microservices.candidate.domain.ContractPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractPlanRepo extends JpaRepository <ContractPlan,Integer> {

}
