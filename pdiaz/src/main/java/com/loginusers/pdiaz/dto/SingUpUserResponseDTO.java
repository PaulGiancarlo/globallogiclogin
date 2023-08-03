package com.loginusers.pdiaz.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SingUpUserResponseDTO implements Serializable {
    private Integer id;
    private Long created;
    private Long lastLogin;
    private String token;
    private Boolean isActive;

}
