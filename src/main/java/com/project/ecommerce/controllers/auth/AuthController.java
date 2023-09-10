package com.project.ecommerce.controllers.auth;


import com.project.ecommerce.dtos.auth.LoginDTO;
import com.project.ecommerce.dtos.auth.UserLoginDTO;
import com.project.ecommerce.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid UserLoginDTO userModel) {
        return ResponseEntity.ok(loginService.login(userModel));
    }

//    @PostMapping(value = "/register")
//    public ResponseEntity<LoginDTO> register(@RequestBody @Valid PersistRegisterModel persistRegisterModel) {
//        return ResponseEntity.ok(loginService.register(persistRegisterModel));
//    }
}
