package com.hos05020.shopping.controller;

import com.hos05020.shopping.SessionConst;
import com.hos05020.shopping.domain.Member;
import com.hos05020.shopping.exception.MemberNotFoundException;
import com.hos05020.shopping.request.LoginForm;
import com.hos05020.shopping.request.MemberRequest;
import com.hos05020.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {


    private final MemberService memberService;

     @PostMapping("/add")
     public void addMember(@RequestBody @Valid MemberRequest request){
         log.info("new Member");
         memberService.register(request);
     }

     @PostMapping("/login")
    public void login(@RequestBody @Valid LoginForm form ,HttpServletRequest request){
         log.info("login");
         Member member = memberService.login(form.getLoginId(), form.getPassword());
         if (member==null){
             throw new MemberNotFoundException();
         }else{
             HttpSession session = request.getSession();
             session.setAttribute(SessionConst.LOGIN_MEMBER,member);
         }
     }


     @GetMapping("/validate")
     public void homeLogin(HttpServletRequest request, HttpServletResponse response){
         HttpSession session = request.getSession(false);
         if(session==null) {
             response.setStatus(HttpServletResponse.SC_NOT_FOUND);
             return;
         }

         Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
         if(loginMember==null){
             response.setStatus(HttpServletResponse.SC_NOT_FOUND);
             return;
         }else {
             response.setStatus(HttpServletResponse.SC_OK);
             return;
         }
     }

     @GetMapping("/logout")
    public void logOut(HttpServletRequest request){
         HttpSession session = request.getSession(false);
         if(session!=null){
             session.invalidate();
         }
         return;
     }

}
