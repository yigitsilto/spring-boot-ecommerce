package com.project.ecommerce.controllers;

import com.project.ecommerce.entities.UserEntity;
import com.project.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/test")
@CrossOrigin("*")
public class TestController {

    private final UserRepository userRepository;

    @GetMapping
    public String test(){

        return userRepository.findById(UUID.fromString("92924d6f-acb3-4fa2-aa65-06d0f1d32b01")).get().getEmail();
    }
}
