package com.loginusers.pdiaz.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Phone implements Serializable {

    private Long number;
    private Integer citycode;
    private String contrycode;
    
}
