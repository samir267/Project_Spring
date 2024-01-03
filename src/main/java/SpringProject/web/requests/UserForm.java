package SpringProject.web.requests;


import SpringProject.dao.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String full_name;
    private Integer phone;
    private String email;
    private String password;
    private String address;
    private Role role;


}
