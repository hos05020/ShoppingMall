package com.hos05020.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Member extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String loginId;

    private String password;

    private String nickname;



}
