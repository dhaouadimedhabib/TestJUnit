package com.esprit.microservices.candidate.repo;

import com.esprit.microservices.candidate.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Integer> {







    @Query("Select p from Plan  p where p.picture is not NULL ")
    List<Plan> picturenotnull();
}
