package com.loginusers.pdiaz.service.impl;


import com.loginusers.pdiaz.entity.User;
import com.loginusers.pdiaz.repository.UserLoginRepository;
import com.loginusers.pdiaz.service.LoginService;
import com.loginusers.pdiaz.dto.SingUpUserResponseDTO;
import com.loginusers.pdiaz.dto.SingUpUserDTO;

import com.loginusers.pdiaz.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserLoginRepository repository;

    public LoginServiceImpl(UserLoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public SingUpUserResponseDTO singUpNewUser(SingUpUserDTO singUpUser) throws Exception {
        SingUpUserResponseDTO userToRegister = new SingUpUserResponseDTO();

        if (validateUser(singUpUser)) {

            UUID uuid = UUID.randomUUID();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            JwtUtil utilObj = new JwtUtil();
            userToRegister.setId(uuid.toString());
            userToRegister.setToken(utilObj.generateToken(uuid.toString()));
            userToRegister.setIsActive(Boolean.TRUE);
            userToRegister.setCreated(timestamp.getTime());
            userToRegister.setLastLogin(timestamp.getTime());

        } else {
            throw new Exception("Error");
        }

        return userToRegister;

    }

    @Override
    public SingUpUserResponseDTO getSingleUser(String id) {
        Optional<User> singUpUser = repository.findById(id);

        SingUpUserResponseDTO response = new SingUpUserResponseDTO();
        if (singUpUser.isPresent()) {
            response.setId(singUpUser.get().getId());

        }
        return response;
    }

    private boolean validateUser(SingUpUserDTO singUpUser) throws Exception {
        try {
            if (singUpUser != null && isValidEmail(singUpUser.getEmail())
                    && isValidPassword(singUpUser.getPassword())) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            throw new Exception("usuario no valido", e.getCause());

        }

        return Boolean.FALSE;

    }

    boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-z]).{8,12}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
