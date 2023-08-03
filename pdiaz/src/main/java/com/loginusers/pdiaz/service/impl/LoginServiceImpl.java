package com.loginusers.pdiaz.service.impl;


import com.loginusers.pdiaz.constants.Constants;
import com.loginusers.pdiaz.exceptions.ApiError;
import com.loginusers.pdiaz.exceptions.ApiTypeError;
import com.loginusers.pdiaz.exceptions.InvalidInputException;
import com.loginusers.pdiaz.repository.UserLoginRepository;
import com.loginusers.pdiaz.service.LoginService;
import com.loginusers.pdiaz.dto.SingUpUserResponseDTO;
import com.loginusers.pdiaz.dto.SingUpUserDTO;

import com.loginusers.pdiaz.util.JwtUtil;
import com.loginusers.pdiaz.util.UserMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public SingUpUserResponseDTO singUpNewUser(SingUpUserDTO singUpUser) throws ApiError {
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
            throw new ApiError(ApiTypeError.BUISINESS, new Timestamp(System.currentTimeMillis()).toLocalDateTime(),Constants.ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return userToRegister;

    }


    private boolean validateUser(SingUpUserDTO singUpUser) throws ApiError {
        try {
            if (singUpUser != null && isValidEmail(singUpUser.getEmail())
                    && isValidPassword(singUpUser.getPassword())) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            throw new ApiError(ApiTypeError.BUISINESS, new Timestamp(System.currentTimeMillis()).toLocalDateTime(),Constants.ERROR, HttpStatus.UNPROCESSABLE_ENTITY);

        }

        return Boolean.FALSE;

    }

    boolean isValidEmail(String email) throws ApiError {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return matcher.matches();
        }
        else{
            throw new ApiError(ApiTypeError.BUISINESS, new Timestamp(System.currentTimeMillis()).toLocalDateTime(),Constants.EMAIL_MSG_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    public boolean isValidPassword(String password) throws ApiError {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-z]).{8,12}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){return matcher.matches();}
        else{
            throw new ApiError(ApiTypeError.BUISINESS, new Timestamp(System.currentTimeMillis()).toLocalDateTime(),Constants.PASSWORD_MSG_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

}
