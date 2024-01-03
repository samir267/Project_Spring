package SpringProject.business.service;

import java.util.List;
import java.util.Optional;

import SpringProject.dao.models.Role;
import SpringProject.dao.models.User;
import SpringProject.web.requests.UserForm;

public interface UserService {
    
	List<User> getAllUsers() ;
	Optional<User> getUserById(Long id);
	User saveUser(UserForm user) ;
	User updateUser(Long id, User updatedUser) throws Exception;
	
	
	 void deleteUser(Long id) ;
	 List<User> getAllUsersByRole(Role role);
   }