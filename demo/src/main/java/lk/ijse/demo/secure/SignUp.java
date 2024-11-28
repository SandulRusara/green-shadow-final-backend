package lk.ijse.demo.secure;

import jakarta.persistence.Access;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUp {
    @NotNull(message = "Id genarate by backend")
    private String user_id;

    @NotNull(message = "Email Can`t be Null ")
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String role;

}
