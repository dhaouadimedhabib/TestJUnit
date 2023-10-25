package com.esprit.microservices.candidate.service;

import com.esprit.microservices.candidate.domain.ContractPlan;
import com.esprit.microservices.candidate.domain.Plan;
import com.esprit.microservices.candidate.domain.User;
import com.esprit.microservices.candidate.repo.ContractPlanRepo;
import com.esprit.microservices.candidate.repo.PlanRepo;
import com.esprit.microservices.candidate.repo.UserRepo;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContractPlanservice implements IContractPlan{
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PlanRepo planRepository;
   @Autowired
    ContractPlanRepo contractPlanRepo;

    public int create(ContractPlan c, int userid, int planid)  {
        User user = userRepository.findById(userid).get();
        Plan plan = planRepository.findById(planid).get();
        Double price = plan.getPrice();
        Double realizationprice = plan.getRealizationprice();
        c.setPlanUser(user);
        c.setPlanContractPlan(plan);
        List<ContractPlan> contractPlanList = contractPlanRepo.findAll();
        c.setPrice(price + realizationprice);
        for (ContractPlan contractPlan : contractPlanList) {
            if (contractPlan.getPlanUser().getUserid() == userid && contractPlan.getStatus().equals("Payed") )  {
                c.setPrice((c.getPlanContractPlan().getPrice()+c.getPlanContractPlan().getRealizationprice()) * 0.95);
            }
            else {
                c.setPrice(price + realizationprice);
            }
        }

        return contractPlanRepo.save(c).getContractId();
    }
    @Override
    public List<ContractPlan> findAllContract() {
        return contractPlanRepo.findAll();
    }
    @Override
    public void update(ContractPlan c) {
        if (contractPlanRepo.findById(c.getContractId()).isPresent())
            contractPlanRepo.save(c);
        else
            System.out.println("doesnt exist");

    }

    @Override
    public boolean delete(int id) {
        if (contractPlanRepo.existsById(id)) {
            contractPlanRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public double Revnnu(int userId) {
        List<Plan> plansListByUser=new ArrayList<>();
        List<Plan> plansList =planRepository.findAll();
        for (Plan plan:plansList) {
            if (plan.getUser().getUserid()==userId){
                plansListByUser.add(plan);
            }
        }
        List<ContractPlan> planContractListUser=new ArrayList<>();
        List<ContractPlan> planContractList=contractPlanRepo.findAll();
        for (ContractPlan contractPlan:planContractList){
            for (Plan plan:plansListByUser){
                if (contractPlan.getPlanContractPlan().getPlanid()==plan.getPlanid()){
                    planContractListUser.add(contractPlan);
                }}
        }

        Double totalRevenue = 0.0;
        for (ContractPlan contractPlan : planContractListUser) {
            totalRevenue += contractPlan.getPrice();
        }
        return totalRevenue;
    }
    @Override
    public ContractPlan get(final Integer contractId) {
        return contractPlanRepo.findById(contractId)
                .orElseThrow(NoSuchElementException::new);
    }



}
