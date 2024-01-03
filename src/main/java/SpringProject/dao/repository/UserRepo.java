package SpringProject.dao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringProject.dao.models.Role;
import SpringProject.dao.models.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
	User findByEmail (String email);

	List<User> findByRole(Role role);


    
}