package SpringProject.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringProject.dao.models.Contracts;

public interface ContractsRepo extends JpaRepository<Contracts,Long>{
    List<Contracts>findContractById(Long id);
}