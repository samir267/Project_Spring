package SpringProject.business.serviceimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import SpringProject.business.service.UserService;
import SpringProject.dao.models.Role;
import SpringProject.dao.models.User;
import SpringProject.dao.repository.UserRepo;
import SpringProject.web.requests.UserForm;

@Service
public class UserImpService implements UserService{

	  @Autowired
	    private UserRepo userRepository;

	  @Autowired
		private PasswordEncoder passwordEncoder;
		
	  	@Override
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }
	 	@Override
	    public Optional<User> getUserById(Long id) {
	        return userRepository.findById(id);
	    }
	 	

	 	@Override
	    public User saveUser(UserForm userForm) {
	 		
	 		if(userForm.getRole()==null)
			{
				userForm.setRole(Role.USER);
				}
			User user = new User(
					null, 
					userForm.getFull_name(),
					userForm.getEmail(),
					userForm.getPhone(), 
					userForm.getAddress(),
					passwordEncoder.encode(userForm.getPassword()),
					userForm.getRole());
			
			return userRepository.save(user);
		
	 			    
	 	}

	 	@Override
	    public User updateUser(Long id, User updatedUser) throws Exception {
	        Optional<User> existingUserOptional = userRepository.findById(id);

	        if (existingUserOptional.isPresent()) {
	            User existingUser = existingUserOptional.get();

	            if (updatedUser.getFull_name() != null) {
	                existingUser.setFull_name(updatedUser.getFull_name());
	            }
	            if (updatedUser.getEmail() != null) {
	                existingUser.setEmail(updatedUser.getEmail());
	            }
	            if (updatedUser.getPhone() != null) {
	                existingUser.setPhone(updatedUser.getPhone());
	            }
	            if (updatedUser.getAddress() != null) {
	                existingUser.setAddress(updatedUser.getAddress());
	            }
	            if (updatedUser.getRole() != null) {
	                existingUser.setRole(updatedUser.getRole());
	            }

	            return userRepository.save(existingUser);
	        }else{
	            throw new Exception("note found user"+id);
	        }
	        
	    }
	 	@Override
	    public void deleteUser(Long id) {
	        userRepository.deleteById(id);
	    }


		@Override
		public List<User> getAllUsersByRole(Role role) {
			return userRepository.findByRole(role);
		}
}
