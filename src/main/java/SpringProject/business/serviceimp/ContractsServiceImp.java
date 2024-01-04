package SpringProject.business.serviceimp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringProject.business.service.ContractsService;
import SpringProject.dao.models.Contracts;
import SpringProject.dao.repository.ContractsRepo;


@Service
public class ContractsServiceImp implements ContractsService{
    
    @Autowired
    private ContractsRepo contractsRepo;

    @Override
     public Contracts createContract(Contracts contract) {
        return contractsRepo.save(contract);
    }
    @Override
    public List<Contracts> listAllContracts() {
        return contractsRepo.findAll();
    }

    
     

}