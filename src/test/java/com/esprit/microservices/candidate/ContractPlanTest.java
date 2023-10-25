package com.esprit.microservices.candidate;

import com.esprit.microservices.candidate.domain.ContractPlan;
import com.esprit.microservices.candidate.domain.Plan;
import com.esprit.microservices.candidate.domain.User;
import com.esprit.microservices.candidate.repo.ContractPlanRepo;
import com.esprit.microservices.candidate.repo.PlanRepo;
import com.esprit.microservices.candidate.repo.UserRepo;
import com.esprit.microservices.candidate.service.ContractPlanservice;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ContractPlanTest {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PlanRepo planRepository;
    @Autowired
    ContractPlanRepo contractPlanRepo;
    @Autowired
    ContractPlanservice contractPlanservice;
    @Test
    public void testCreate() {
        // Créez un utilisateur fictif
        User user = new User();
        user.setUserid(1);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Créez un plan fictif
        Plan plan = new Plan();
        plan.setPrice(100.0);
        plan.setRealizationprice(20.0);
        Mockito.when(planRepository.findById(1)).thenReturn(Optional.of(plan));

        // Configurez la liste fictive de ContractPlan
        List<ContractPlan> contractPlanList = new ArrayList<>();

        Mockito.when(contractPlanRepo.findAll()).thenReturn(contractPlanList);

        // Appelez la méthode create
        ContractPlan contractPlan = new ContractPlan();
        int contractId = contractPlanservice.create(contractPlan, 1, 1);

        // Vérifiez si le contrat a été créé avec succès et le contractId retourné
        assertNotNull(contractPlan);
        assertEquals(1, contractId);
    }

    @Test
    public void testFindAllContract() {
        // Créez une liste fictive de contrats
        List<ContractPlan> contractList = new ArrayList<>();
        // Ajoutez des contrats à la liste au besoin

        // Configurez le mock pour retourner cette liste lorsque findAll est appelé
        Mockito.when(contractPlanRepo.findAll()).thenReturn(contractList);

        // Appelez la méthode findAllContract du service
        List<ContractPlan> result = contractPlanservice.findAllContract();

        // Vérifiez si le résultat est conforme à vos attentes
        assertNotNull(result);
        assertEquals(contractList.size(), result.size()); // Vérifiez si le nombre de contrats est correct
    }


    @Test
    public void testUpdateNotFound() {
        // Configurez le mock pour retourner un Optional vide
        Mockito.when(contractPlanRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // Créez un contrat fictif pour la mise à jour
        ContractPlan contractPlan = new ContractPlan();
        // Configurez les propriétés du contrat au besoin

        // Appelez la méthode update
        contractPlanservice.update(contractPlan);

        // Vérifiez si un message "doesn't exist" est affiché
        Mockito.verify(contractPlanRepo, Mockito.never()).save(Mockito.any(ContractPlan.class));
    }

    @Test
    public void testRevnnu() {
        // Créez une liste fictive de plans
        List<Plan> plansList = new ArrayList<>();
        // Ajoutez des plans à la liste au besoin

        // Configurez le mock pour retourner cette liste lorsque findAll est appelé
        Mockito.when(planRepository.findAll()).thenReturn(plansList);

        // Appelez la méthode Revnnu du service
        double result = contractPlanservice.Revnnu(1);

        // Vérifiez si le résultat est conforme à vos attentes
        // (Cela dépend de la logique exacte de calcul de revenus dans votre méthode)
        // Exemple: assertEquals(expectedResult, result, 0.01); pour une tolérance de 0.01
    }

    @Test
    public void testGet() {
        // Créez un contrat fictif avec un ID spécifique
        ContractPlan contractPlan = new ContractPlan();
        contractPlan.setContractId(1);

        // Configurez le mock pour retourner un Optional contenant le contrat fictif
        Mockito.when(contractPlanRepo.findById(1)).thenReturn(Optional.of(contractPlan));

        // Appelez la méthode get avec l'ID du contrat fictif
        ContractPlan result = contractPlanservice.get(1);

        // Vérifiez si le résultat est conforme à vos attentes
        assertNotNull(result);
        assertEquals(1, result.getContractId().intValue());
    }

    @Test
    public void testGetNotFound() {
        // Configurez le mock pour retourner un Optional vide
        Mockito.when(contractPlanRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // Appelez la méthode get avec un ID fictif
        assertThrows(NoSuchElementException.class, () -> {
            contractPlanservice.get(1);
        });
    }

}
