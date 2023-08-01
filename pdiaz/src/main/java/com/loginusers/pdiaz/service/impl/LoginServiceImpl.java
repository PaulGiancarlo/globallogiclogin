package com.loginusers.pdiaz.service.impl;


import com.loginusers.pdiaz.constants.Constants;
import com.loginusers.pdiaz.entity.User;
import com.loginusers.pdiaz.exceptions.InvalidInputException;
import com.loginusers.pdiaz.repository.UserLoginRepository;
import com.loginusers.pdiaz.service.LoginService;
import com.loginusers.pdiaz.dto.SingUpUserResponseDTO;
import com.loginusers.pdiaz.dto.SingUpUserDTO;

import com.loginusers.pdiaz.util.JwtUtil;
import com.loginusers.pdiaz.util.UserMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
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

            repository.saveUserWithPhones(UserMapperUtil.mapUserToResponse(singUpUser,userToRegister));
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

    private boolean validateUser(SingUpUserDTO singUpUser) throws InvalidInputException {
        try {
            if (singUpUser != null && isValidEmail(singUpUser.getEmail())
                    && isValidPassword(singUpUser.getPassword())) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            throw new InvalidInputException("usuario no valido");

        }

        return Boolean.FALSE;

    }

    boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return matcher.matches();
        }
        else{
            throw new InvalidInputException(Constants.EMAIL_MSG_ERROR);
        }

    }

    public boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-z]).{8,12}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){return matcher.matches();}
        else{
            throw new InvalidInputException(Constants.PASSWORD_MSG_ERROR);
        }

    }

}
