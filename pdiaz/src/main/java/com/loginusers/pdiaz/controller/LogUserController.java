package com.loginusers.pdiaz.controller;

import com.loginusers.pdiaz.dto.SingUpUserResponseDTO;
import com.loginusers.pdiaz.service.LoginService;
import com.loginusers.pdiaz.dto.SingUpUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class LogUserController {

    @Autowired
    private LoginService service;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<SingUpUserResponseDTO> singUpNewUser(@RequestBody SingUpUserDTO singUpUser) throws Exception {
        return new ResponseEntity<>(service.singUpNewUser(singUpUser), HttpStatus.CREATED);

    }

}
