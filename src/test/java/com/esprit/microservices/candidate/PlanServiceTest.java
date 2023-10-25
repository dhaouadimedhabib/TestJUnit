package com.esprit.microservices.candidate;



import com.esprit.microservices.candidate.domain.ContractPlan;
import com.esprit.microservices.candidate.domain.Plan;
import com.esprit.microservices.candidate.domain.User;
import com.esprit.microservices.candidate.model.PlanDTO;
import com.esprit.microservices.candidate.repo.PlanRepo;
import com.esprit.microservices.candidate.repo.UserRepo;
import com.esprit.microservices.candidate.service.Planservice;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class PlanServiceTest {
    @Autowired
    private Planservice planService;

    @Autowired
    private PlanRepo planRepo;

    @Autowired
    private UserRepo userRepo;



    @Test
    public void testCreate() throws Exception {
        // Créez un utilisateur fictif pour le test
        User user = new User();
        user.setFirstname("testuser");
        userRepo.save(user);

        // Créez un plan fictif pour le test
        Plan plan = new Plan();
        // Settez les propriétés du plan au besoin

        // Appelez la méthode create
        Plan resultPlan = planService.create(plan, user.getUserid());

        // Vérifiez si le plan a été correctement créé et lié à l'utilisateur
        assertNotNull(resultPlan);
        assertNotNull(resultPlan.getUser().getUserid());
        assertEquals(user, resultPlan.getUser());

        // Supprimez le plan
        planRepo.deleteById(resultPlan.getPlanid());
        userRepo.delete(user);
    }
    @Test
    public void testFindAll() {
        // Créez une liste fictive de Plans
        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan());
        plans.add(new Plan());

        // Configurez le mock pour retourner cette liste lorsque findAll est appelé
        Mockito.when(planRepo.findAll(Sort.by("planid"))).thenReturn(plans);

        // Appelez la méthode findAll du service
        List<PlanDTO> result = planService.findAll();

        // Vérifiez si le résultat est conforme à vos attentes
        assertNotNull(result);
        assertEquals(2, result.size()); // Vérifiez si deux Plans sont renvoyés
    }
    @Test
    public void testGetFound() {
        // Créez un plan fictif
        Plan plan = new Plan();
        plan.setPlanid(1);

        // Configurez le mock pour retourner un Optional contenant le plan fictif
        Mockito.when(planRepo.findById(1)).thenReturn(Optional.of(plan));

        // Appelez la méthode get avec l'ID du plan fictif
        PlanDTO result = planService.get(1);

        // Vérifiez si le résultat est conforme à vos attentes
        assertNotNull(result);
        assertEquals(1, result.getPlanid().intValue());
    }

    @Test
    public void testUpdate() {
        // Configurez le mock pour retourner true lors de l'appel à existsById
        Mockito.when(planRepo.existsById(Mockito.anyInt())).thenReturn(true);

        // Créez un plan fictif pour la mise à jour
        Plan plan = new Plan();
        // Configurez les propriétés du plan au besoin

        // Appelez la méthode update
        boolean result = planService.update(plan);

        // Vérifiez si la mise à jour a réussi
        assertTrue(result);

        // Vérifiez si le plan a été enregistré
        Mockito.verify(planRepo).save(plan);
    }

    @Test
    public void testGetChiffreAffaireByOffer() {
        // Créez un plan fictif avec des contrats fictifs
        Plan plan = new Plan();
        ContractPlan contract1 = new ContractPlan();
        contract1.setPrice(100.0);
        ContractPlan contract2 = new ContractPlan();
        contract2.setPrice(150.0);
        plan.getContractPlans().add(contract1);
        plan.getContractPlans().add(contract2);

        // Configurez le mock pour retourner un Optional contenant le plan fictif
        Mockito.when(planRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(plan));

        // Appelez la méthode getChiffreAffaireByOffer
        double chiffreAffaire = planService.getChiffreAffaireByOffer(1);

        // Vérifiez si le chiffre d'affaires est correct
        assertEquals(250.0, chiffreAffaire, 0.01); // Utilisation d'une tolérance de 0.01 pour les valeurs décimales
    }
}
