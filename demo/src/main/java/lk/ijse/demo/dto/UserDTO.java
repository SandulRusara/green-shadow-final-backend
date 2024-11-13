package lk.ijse.demo.dto;

import com.example.demo.dto.SuperDTO;
import com.example.demo.dto.UserStatus;
import com.example.demo.entity.Role;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO , UserStatus {
    @Id
    private String email;
    private String password;
    private Role role;
}
