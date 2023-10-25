package com.esprit.microservices.candidate.service;

import com.esprit.microservices.candidate.domain.ContractPlan;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import java.util.List;


public interface IContractPlan {
   // int create(ContractPlan c, int userid, int planid) throws MessagingException;

    List<ContractPlan> findAllContract();

    void update(ContractPlan c);

    boolean delete(int id);

    double Revnnu(int userId);

    ContractPlan get(Integer contractId);
}
