package SpringProject.business.serviceimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringProject.business.service.CarService;
import SpringProject.dao.models.Car;
import SpringProject.dao.repository.CarRepo;

@Service
public class CarServiceImp implements CarService{

     @Autowired
    private CarRepo carRepository;
    
    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }


    @Override
    public Car updateCar(Long id, Car updatedCar)throws Exception {
        Optional<Car> existingCarOptional = carRepository.findById(id);

        if (existingCarOptional.isPresent()) {
            Car existingCar = existingCarOptional.get();
            if (updatedCar.getModel() != null) {
                existingCar.setModel(updatedCar.getModel());
            }
            if (updatedCar.getName() != null) {
                existingCar.setName(updatedCar.getName());
            }
            if (updatedCar.getCategory() != null) {
                existingCar.setCategory(updatedCar.getCategory());
            }
            if (updatedCar.getPrice() != null) {
                existingCar.setPrice(updatedCar.getPrice());
            }
            if (updatedCar.getNb_places() != null) {
                existingCar.setNb_places(updatedCar.getNb_places());
            }
          
                existingCar.setEtat(updatedCar.getEtat());
            

            return carRepository.save(existingCar);
        } else {
            throw new Exception("Car not found with id: " + id);
        }
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}