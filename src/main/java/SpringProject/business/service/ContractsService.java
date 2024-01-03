package SpringProject.business.service;

import java.util.List;

import SpringProject.dao.models.Contracts;

public interface ContractsService {
    
    Contracts createContract(Contracts contract);
    List<Contracts> listAllContracts();
}