package com.esprit.microservices.candidate.service;

import com.esprit.microservices.candidate.domain.ContractPlan;
import com.esprit.microservices.candidate.domain.Plan;
import com.esprit.microservices.candidate.domain.User;
import com.esprit.microservices.candidate.model.PlanDTO;
import com.esprit.microservices.candidate.repo.PlanRepo;
import com.esprit.microservices.candidate.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.esprit.microservices.candidate.util.NotFoundException;


@Service
public class Planservice implements IPlan{
    @Autowired
    PlanRepo planRepo;
@Autowired
    UserRepo userRepo;
    @Override
    public Plan create(Plan p, int userId) throws Exception {
        User user = userRepo.findById(userId).get();
        // User user = userService.getUserByToken(request);
        p.setUser(user);
        return planRepo.save(p);
    }
    @Override
    public List<PlanDTO> findAll() {
        final List<Plan> plans = planRepo.findAll(Sort.by("planid"));
        return plans.stream()
                .map((plan) -> mapToDTO(plan, new PlanDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlanDTO> PLAN_DTOS() {
        final List<Plan> plans = planRepo.picturenotnull();
        return plans.stream()
                .map((plan) -> mapToDTO(plan, new PlanDTO()))
                .collect(Collectors.toList());
    }
    @Override
    public PlanDTO get(final Integer planid) {
        return planRepo.findById(planid)
                .map(plan -> mapToDTO(plan, new PlanDTO()))
                .orElseThrow(NotFoundException::new);
    }
    private PlanDTO mapToDTO(final Plan plan, final PlanDTO planDTO) {
        planDTO.setPlanid(plan.getPlanid());
        planDTO.setTitle(plan.getTitle());
        planDTO.setPicture(plan.getPicture());
        planDTO.setPrice(plan.getPrice());
        planDTO.setRealizationprice(plan.getRealizationprice());
        planDTO.setLivingroom(plan.getLivingroom());
        planDTO.setKitchen(plan.getKitchen());
        planDTO.setWc(plan.getWc());
        planDTO.setRoom1(plan.getRoom1());
        planDTO.setRoom2(plan.getRoom2());
        planDTO.setDescription(plan.getDescription());
        return planDTO;
    }
    @Override
    public Boolean update(Plan c) {

        if(planRepo.existsById(c.getPlanid())){
            planRepo.save(c).equals(c);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        if (planRepo.existsById(id)) {
            planRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public double getChiffreAffaireByOffer(int offerId) {
        Plan plan= planRepo.findById(offerId).get();
        Set<ContractPlan> ContractList=plan.getContractPlans();
        double prix=0.0;
        for (ContractPlan contract:ContractList){
            prix+= contract.getPrice();
        }
        return prix;
    }

    @Override
    public Plan savePlan(Plan plan, /*@NonNull HttpServletRequest request*/ int id) throws Exception {
        // sendSMS("+","hello");
        // User user = userService.getUserByToken(request);
        User user = userRepo.findById(id).get();
        plan.setUser(user);
        return planRepo.save(plan);
    }

    @Override
    public int addplan1(Plan plan, int idUser) {
        User user=userRepo.findById(idUser).get();


        plan.setUser(user);
        return planRepo.save(plan).getPlanid();
    }

}
