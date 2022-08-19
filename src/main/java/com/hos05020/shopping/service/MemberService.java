package com.hos05020.shopping.service;

import com.hos05020.shopping.domain.Member;
import com.hos05020.shopping.exception.MemberNotFoundException;
import com.hos05020.shopping.repository.MemberRepository;
import com.hos05020.shopping.request.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public void register(MemberRequest request){
        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .build();

        memberRepository.save(member);
    }

    public Member login(String loginId,String password){
        Member member = memberRepository.findMemberByLoginId(loginId).orElseThrow(() -> new MemberNotFoundException());
        if(member.getPassword().equals(password)){
            return member;
        }else{
            return null;
        }
    }



}
