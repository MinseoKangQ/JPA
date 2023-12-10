package com.MinseoKangQ.jpashopapplication.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address { // 내장 타입
    private String city;
    private String street;
    private String zipcode;
}
