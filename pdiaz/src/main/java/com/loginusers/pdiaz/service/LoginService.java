package com.loginusers.pdiaz.service;

import com.loginusers.pdiaz.dto.SingUpUserResponseDTO;
import com.loginusers.pdiaz.dto.SingUpUserDTO;

public interface LoginService {
    public SingUpUserResponseDTO singUpNewUser(SingUpUserDTO singUpUser)  throws  Exception;
    public SingUpUserResponseDTO getSingleUser(Integer id);
}
