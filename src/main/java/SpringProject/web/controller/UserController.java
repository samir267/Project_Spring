package SpringProject.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import SpringProject.business.service.UserService;
import SpringProject.dao.models.Car;
import SpringProject.dao.models.Role;
import SpringProject.dao.models.User;
import SpringProject.dao.repository.UserRepo;
import SpringProject.web.requests.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	UserDetailsService userDetailsService;
	

    @Autowired
    private UserService userService;
        @Autowired
    private UserRepo userRepo;
      


        // @GetMapping("/allUsers2")
        // public String getAllUsers2(Model model) {
        //     List<User> users = userService.getAllUsers();
        //     model.addAttribute("users", users);
        //     return "UsersList";
        // }


        @GetMapping("/allUsers2")
        public String getAllUsers(Model model) {
            List<User> users = userService.getAllUsersByRole(Role.USER);
            model.addAttribute("users", users);
            return "UsersList";
        }
    
        @GetMapping("/login")
        public String login() {
            return "login";
        }
        

        
        
        
        @GetMapping("/register")
        public String getRegistrationPage(@ModelAttribute("user") UserForm userForm) {
            return "register";
        }
        
	
	@PostMapping("/register")
	public String saveUser(@ModelAttribute("user") UserForm userForm, Model model) {
		userService.saveUser(userForm);
		model.addAttribute("message", "Registered Successfuly!");
		return "login";
	}
	

	

    
    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/editUser/{id}")
    public String showEditCarForm(@PathVariable Long id, Model model) throws Exception {
        try {
            Optional<User> existingUser = userService.getUserById(id);
    
            model.addAttribute("user", existingUser);
    
            return "editUser";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "User not found");
            return "errorView"; 
        }
    }

   
@PostMapping("/updateUser")
public String updateCar(@ModelAttribute("user") User updatedUser, Model model) throws Exception {
    try {
        User existingUser = userRepo.findByEmail(updatedUser.getEmail());
        
        if (existingUser != null && !existingUser.getId().equals(updatedUser.getId())) {
            model.addAttribute("errorMessage", "Email already in use by another user");
            return "errorView";
        }

        userService.updateUser(updatedUser.getId(), updatedUser);
        return "redirect:/allUsers";
    } catch (NotFoundException e) {
        model.addAttribute("errorMessage", "User not found");
        return "errorView"; 
    }
}


    
@GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id")Long id){
            if (userService.getUserById(id).isPresent()) {
            userService.deleteUser(id);
                }
        return "redirect:/allUsers2";
    }
   

     @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Récupérer les détails d'authentification actuels de l'utilisateur
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Si l'authentification est présente, terminer la session
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        
        // Rediriger vers la page de connexion avec un message de déconnexion
        return "redirect:/login";
    }
}
