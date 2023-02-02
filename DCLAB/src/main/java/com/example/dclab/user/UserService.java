package com.example.dclab.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void create(UserCreateForm userCreateForm) { // 계정 받고 DB에 저장하기
        User user = User.builder()
                .email(userCreateForm.getEmail())
                .username(userCreateForm.getUsername())
                .password(passwordEncoder.encode(userCreateForm.getPassword()))
                .build();

        userRepository.save(user); // 저장
    }

    public boolean login(UserForm userForm){
        User finduser = userRepository.findByEmail(userForm.getEmail()); // DB에 저장되어 있는 데이터 중 이메일로 유저 찾기 (이메일 중복X)

        if (finduser == null){
            return false;
        }

        if(!passwordEncoder.matches(userForm.getPassword(), finduser.getPassword())){
            // DB에 저장되어있는 암호화된 비번과 유저가 입력한 비번을 암호화 했을 때 맞는지 확인
            return false;
        }

        return true;
    }


}
