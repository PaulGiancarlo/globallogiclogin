package com.loginusers.pdiaz.dto;

import com.loginusers.pdiaz.dto.Phone;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SingUpUserDTO implements Serializable {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
}
