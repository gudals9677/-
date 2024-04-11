package kr.co.springboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.springboard.dto.TermsDTO;
import kr.co.springboard.dto.UserDTO;
import kr.co.springboard.service.TermsService;
import kr.co.springboard.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class UserController {

    private final TermsService termsService;
    private final UserService userService;

    @GetMapping("/user/login")
    public String loginForm(){
        return "/user/login";
    }

    @GetMapping("/user/terms")
    public String terms(Model model){

        TermsDTO termsResult = termsService.selectTerms();
        model.addAttribute("termResult",termsResult);

        return "/user/terms";
    }

    @GetMapping("/user/register")
    public String registerForm(){
        return "/user/register";
    }

    @PostMapping("/user/register")
    public String register(HttpServletRequest request, UserDTO userDTO){

        String regip = request.getRemoteAddr();
        userDTO.setRegip(regip);

        log.info(userDTO.toString());

        userService.insertUser(userDTO);

        return "redirect:/user/login";
    }

}
