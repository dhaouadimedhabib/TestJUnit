package com.esprit.microservices.candidate.domain;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ContractPlan {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;

    @Column

    private LocalDate contractDate;

    @Column

    private Double price;
    @Column
    private String statut;
    @ManyToOne
    @JoinColumn(name = "plan_user_id") // Colonne de jointure dans la table ContractPlan
    private User planUser;

    @ManyToOne

    @JoinColumn(name = "plan_contract_plan_id")  // Assurez-vous que le nom correspond à la colonne de la table ContractPlan
    private Plan planContractPlan;



    public int getIdContrat() {
        return this.contractId;
    }

    public String getStatus() {
        return this.statut;
    }
}

