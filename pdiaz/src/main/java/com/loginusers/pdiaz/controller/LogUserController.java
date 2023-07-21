package com.loginusers.pdiaz.controller;

import com.loginusers.pdiaz.dto.SingUpUserResponseDTO;
import com.loginusers.pdiaz.service.LoginService;
import com.loginusers.pdiaz.dto.SingUpUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value ="/api")
public class LogUserController {
    
    @Autowired
    private LoginService service;
    @Autowired
    private HttpServletRequest request;


@PostMapping(value ="/sing-up")
public ResponseEntity<SingUpUserResponseDTO> singUpNewUser(@RequestBody SingUpUserDTO singUpUser, HttpServletRequest request) throws Exception {
    return new ResponseEntity<>(service.singUpNewUser(singUpUser), HttpStatus.CREATED);

}


}
