package SpringProject.web.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SpringProject.business.service.CarService;
import SpringProject.business.service.ContractsService;
import SpringProject.business.service.UserService;
import SpringProject.dao.models.Car;
import SpringProject.dao.models.Contracts;
import SpringProject.dao.models.Role;
import SpringProject.dao.models.User;
import SpringProject.dao.repository.ContractsRepo;

@Controller
@RequestMapping("")
public class ConractsController {

    @Autowired
    private ContractsService contractsService;
    @Autowired
    private CarService carService;

     @Autowired
    private UserService userService;

    @Autowired
    private ContractsRepo contractsRepo;
    

        @GetMapping("/allContract")
        public String getAllContracts(Model model) {
            List<Contracts> contracts = contractsService.listAllContracts();
            model.addAttribute("Contracts", contracts);
            
            LocalDateTime currentDateTime = LocalDateTime.now();
            for (Contracts contract : contracts) {
                if (contract.getDate_fin().isBefore(currentDateTime)) {
                    Optional<Car> optionalCar = carService.getCarById(contract.getId_car());
                    optionalCar.ifPresent(car -> {
                        car.setEtat(0);
                        carService.saveCar(car);  
                    });
                }
            }
            return "ContractsList";
        }


        
        

    @GetMapping("/newContractForm1/{id}")
    public String showNewContractForm(@PathVariable Long id,Model model) {
        Contracts contract = new Contracts();
        model.addAttribute("contract", contract);
        model.addAttribute("idCar", id);

        List<User> users = userService.getAllUsersByRole(Role.USER);
            model.addAttribute("users", users);

        return "newContract";
    }

    @PostMapping("/newContract1/{id}")
    public String createNewContract(@PathVariable Long id,@ModelAttribute("contract") Contracts contract,@RequestParam("userId") Long userId) {
        Optional<Car>Car=carService.getCarById(id);
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (contract.getDate_deb().isBefore(currentDateTime)) {

        Car.get().setEtat(1);}
        int daysBetween = (int) ChronoUnit.DAYS.between(contract.getDate_deb(), contract.getDate_fin());
        int prix=Integer.parseInt(Car.get().getPrice() )*daysBetween;
        contract.setId_car(Car.get().getId());
        contract.setPrix_Contract(prix);
        contract.setId_user(userId);
        contractsService.createContract(contract);
        return "redirect:/allContract";
    }





}