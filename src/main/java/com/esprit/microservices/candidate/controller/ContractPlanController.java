package com.esprit.microservices.candidate.controller;

import com.esprit.microservices.candidate.domain.ContractPlan;
import com.esprit.microservices.candidate.service.IContractPlan;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contractPlans")
@CrossOrigin(origins = "http://localhost:4200")
public class ContractPlanController {
    @Autowired
    private IContractPlan contratPlan;



/*
    @PostMapping("/addContrat/{userId}/{planId}")
    public ResponseEntity<String> createContractPlan(@RequestBody ContractPlan c,
                                                     @PathVariable("userId") int userId,
                                                     @PathVariable("planId") int planId) throws MessagingException
    {
        try {


            int contractId = contratPlan.create(c, userId, planId);
            String message = "Le contrat avec l'ID " + contractId + " a été créé avec succès.";
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
        catch (MessagingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }}
*/

    @GetMapping("/findall")
    public ResponseEntity<List<ContractPlan>> getAllContractPlans() {
        return ResponseEntity.ok(contratPlan.findAllContract());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractPlan> getContratById(@PathVariable int id) {
        ContractPlan contrat = contratPlan.get(id);
        if (contrat == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(contrat);
        }
    }

    @PutMapping("updateContrat")
    ResponseEntity<String> updateContract(@RequestBody ContractPlan c)
    {
        contratPlan.update(c);
        return ResponseEntity.ok("Le contrat a été mis à jour avec succès.");
    }


    @DeleteMapping("contrat/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") int id) {
        boolean deleted = contratPlan.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
