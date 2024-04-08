package kr.co.springboard.service;

import kr.co.springboard.dto.UserDTO;
import kr.co.springboard.entity.User;
import kr.co.springboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public void insertUser(UserDTO userDTO){

        String encoded = passwordEncoder.encode(userDTO.getPass());
        userDTO.setPass(encoded);
        //userDTO.setRole("USER");

        User user = modelMapper.map(userDTO, User.class);
        log.info("user check {}", user);

        userRepository.save(user);
    }


    public void selectUser(){

    }
    public void selectUsers(){

    }
    public void updateUser(){

    }
    public void deleteUser(){

    }
}
