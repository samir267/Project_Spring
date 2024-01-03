package SpringProject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringProject.dao.models.Car;

@Repository
public interface CarRepo extends JpaRepository<Car,Long> {
    
}