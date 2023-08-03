package com.loginusers.pdiaz.util;

import com.loginusers.pdiaz.dto.SingUpUserDTO;
import com.loginusers.pdiaz.dto.SingUpUserResponseDTO;
import com.loginusers.pdiaz.entity.PhonesType;
import com.loginusers.pdiaz.entity.User;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@UtilityClass
public class UserMapperUtil {

    public User mapUserToResponse(SingUpUserDTO registerUser ,SingUpUserResponseDTO responseDTO){
User user = new User();

user.setToken(responseDTO.getToken());
user.setLastLogin(new Date(responseDTO.getLastLogin()));
user.setIsActive(responseDTO.getIsActive());
user.setDateCreated(new Date(responseDTO.getCreated()));
user.setName(registerUser.getName());
user.setEmail(registerUser.getEmail());
user.setPassword(registerUser.getPassword());
List<PhonesType> userPhones = new ArrayList<>();
registerUser.getPhones().forEach(p->{
    userPhones.add(new PhonesType(p.getNumber(),p.getCitycode(),p.getContrycode()));
});
user.setPhones(userPhones);


        return user;
    }
}
