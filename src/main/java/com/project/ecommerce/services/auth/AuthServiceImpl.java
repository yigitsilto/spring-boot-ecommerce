package com.project.ecommerce.services.auth;

import com.project.ecommerce.auth.JwtUtil;
import com.project.ecommerce.dtos.auth.LoginDTO;
import com.project.ecommerce.dtos.auth.UserLoginDTO;
import com.project.ecommerce.entities.UserEntity;
import com.project.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

//    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    @Override
    public LoginDTO login(UserLoginDTO userModel) {

        UserEntity userEntity = userRepository.findByEmail(userModel.getEmail())
                .orElseThrow(() -> new RuntimeException("user.is.not.exist"));

        checkUserPassword(userModel, userEntity);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        LoginDTO loginModel = new LoginDTO();
        UserDetails userDetails = new User(userEntity.getEmail(), userEntity.getPassword(), authorities);

        loginModel.setAccessToken(jwtUtil.generateToken(userDetails, userEntity));

        return loginModel;
    }


    private void checkUserPassword(UserLoginDTO userModel, UserEntity userEntity) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean passwordsMatch = passwordEncoder.matches(userModel.getPassword(), userEntity.getPassword());

        if (!passwordsMatch) {
            throw new RuntimeException("user.is.not.exist");
        }
    }

}
