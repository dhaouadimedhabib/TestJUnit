package com.esprit.microservices.candidate.controller;


import com.esprit.microservices.candidate.domain.Plan;
import com.esprit.microservices.candidate.model.PlanDTO;
import com.esprit.microservices.candidate.repo.PlanRepo;
import com.esprit.microservices.candidate.repo.UserRepo;
import com.esprit.microservices.candidate.service.IPlan;
import com.esprit.microservices.candidate.service.Planservice;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/plans")
@CrossOrigin(origins = "*")
public class PlanController {


    @Autowired
    private IPlan plan;

    @Autowired
    Planservice planService;
    @Autowired
    UserRepo userRepository;
    @Autowired
    PlanRepo planRepository;

    @PostMapping("/addPlan/{userId}")
    public ResponseEntity<Void> addPlan(@RequestBody Plan p,@PathVariable int userId) throws Exception
    {
        plan.create(p, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addplan1/{userid}")
    public ResponseEntity<String> addContrat(@RequestBody Plan c,@PathVariable int userid){
        int newRentalOfferId = planService.addplan1(c, userid);
        if (newRentalOfferId != 0) {
            String message = "Offre de location ajoutée avec succès avec l'id " + newRentalOfferId;
            return ResponseEntity.ok().body(message);
        } else {
            String message = "Erreur lors de l'ajout de l'offre de location";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
    @GetMapping("/getPlan")
    public ResponseEntity<List<PlanDTO>> getAllPlans()
    {
        List<PlanDTO> plans = plan.findAll();
        return ResponseEntity.ok(plans);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> getPlan(@PathVariable int id) {  PlanDTO contrat = plan.get(id);
        if (contrat == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(contrat);

        }
    }

    @PutMapping("updatePlan")

    public ResponseEntity<String> updateContract(@RequestBody Plan p)
    {
        plan.update(p);
        return ResponseEntity.ok("Le Plan a été mis à jour avec succès.");
    }
    @DeleteMapping("plan/{id}")
    public ResponseEntity<Void> deleteplan(@PathVariable("id") int id) {
        boolean deleted = plan.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/revenueoffer/{offerId}")
    public double calculateRevenueForUser(@PathVariable int offerId) {
        Double revenue = planService.getChiffreAffaireByOffer(offerId);
        if (revenue == null) {
            return 0;
        } else {
            return revenue;
        }
    }



}
