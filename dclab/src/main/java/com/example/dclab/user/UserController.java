package com.example.dclab.user;

import com.example.dclab.user.UserCreateForm;
import com.example.dclab.user.UserForm;
import com.example.dclab.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "/login-form";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserForm userForm, Model model) {
        if(userService.login(userForm)) {
            return "redirect:/";
        }

        model.addAttribute("error", "계정이 존재하지 않습니다.");
        return "/login-form";
    }

    @GetMapping("/signup")
    public String signupForm() {
        // get으로 받을 시 signup-form 보여주기
        return "/signup-form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // 검증 과정에서 오류가 있다면 (ex. userCreateForm == null)
            return "/signup-form"; // 회원가입 폼으로 다시 돌아가기
        }

        userService.create(userCreateForm);
        // 위 조건에 모두 걸리지 않았다면 service.create로 받은 값들 넘기기

        return "/login-form"; // 로그인으로 넘기기
    }


}
