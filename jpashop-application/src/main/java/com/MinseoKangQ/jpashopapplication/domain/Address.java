package com.MinseoKangQ.jpashopapplication.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter // 값 타입은 값 변경을 불가능하게 하기 위해 @Setter 는 제거
public class Address { // 내장 타입

    private String city;
    private String street;
    private String zipcode;

    protected Address() { } // JPA 스펙 상 기본 생성자 protected 로 설정

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
