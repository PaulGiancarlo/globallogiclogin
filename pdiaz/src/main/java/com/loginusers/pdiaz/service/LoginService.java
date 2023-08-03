package com.loginusers.pdiaz.service;

import com.loginusers.pdiaz.dto.SingUpUserResponseDTO;
import com.loginusers.pdiaz.dto.SingUpUserDTO;
import com.loginusers.pdiaz.exceptions.ApiError;


public interface LoginService {
    public SingUpUserResponseDTO singUpNewUser(SingUpUserDTO singUpUser)  throws ApiError;

}
