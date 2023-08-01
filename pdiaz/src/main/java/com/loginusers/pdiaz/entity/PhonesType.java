package com.loginusers.pdiaz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "phones")
public class PhonesType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number")
    private Long number;
    @Column(name = "citycode")
    private Integer citycode;
    @Column(name = "countrycode")
    private String countrycode;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;

    public PhonesType(Long number, Integer citycode, String countrycode) {
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
    }
}
