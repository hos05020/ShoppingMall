package com.hos05020.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String nickname;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(getId(), member.getId()) && Objects.equals(getLoginId(), member.getLoginId()) && Objects.equals(getPassword(), member.getPassword()) && Objects.equals(getNickname(), member.getNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLoginId(), getPassword(), getNickname());
    }
}
