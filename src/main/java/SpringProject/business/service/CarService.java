package SpringProject.business.service;

import java.util.List;
import java.util.Optional;


import SpringProject.dao.models.Car;

public interface CarService {
 

    List<Car> getAllCars();
    Optional<Car> getCarById(Long id);
    Car saveCar(Car car);
    Car updateCar(Long id, Car updatedCar)throws Exception;
    void deleteCar(Long id);

   
}