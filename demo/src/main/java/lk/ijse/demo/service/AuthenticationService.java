package lk.ijse.demo.service;

import lk.ijse.demo.dto.impl.ChangePasswordDTO;
import lk.ijse.demo.response.JwtAuthResponse;
import lk.ijse.demo.secure.SignUp;
import lk.ijse.demo.secure.SingIn;

public interface AuthenticationService {
    JwtAuthResponse singUp (SignUp signUp);
    JwtAuthResponse singIp (SingIn signUp);
    JwtAuthResponse refreshToken(String refreshToken);
    void changePassword(ChangePasswordDTO changePasswordDTO);

}
