package com.project.ecommerce.services.auth;


import com.project.ecommerce.dtos.auth.LoginDTO;
import com.project.ecommerce.dtos.auth.UserLoginDTO;

public interface AuthService {
    LoginDTO login(UserLoginDTO userModel);

//    LoginModel register(PersistRegisterModel persistRegisterModel);

}
