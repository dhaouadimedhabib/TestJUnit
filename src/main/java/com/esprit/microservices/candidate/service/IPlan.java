package com.esprit.microservices.candidate.service;

import com.esprit.microservices.candidate.domain.Plan;
import com.esprit.microservices.candidate.model.PlanDTO;

import java.util.List;

public interface IPlan {
    Plan create(Plan p, int userId) throws Exception;



    PlanDTO get(Integer planid);

    List<PlanDTO> findAll();
    List<PlanDTO> PLAN_DTOS ();

    Boolean update(Plan c);

    boolean delete(int id);

    double getChiffreAffaireByOffer(int offerId);

    Plan savePlan(Plan plan, /*@NonNull HttpServletRequest request*/ int id) throws Exception;

    int addplan1(Plan plan, int idUser);
}
