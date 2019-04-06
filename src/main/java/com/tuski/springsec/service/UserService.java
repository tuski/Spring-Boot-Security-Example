package com.tuski.springsec.service;

import com.tuski.springsec.model.Role;
import com.tuski.springsec.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.tuski.springsec.repository.RoleRepository;
import com.tuski.springsec.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
   // private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
       // this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserInfo findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void saveUser(UserInfo userInfo){
       // userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userInfo.setActive(1);
        Role userRole= roleRepository.findByRole("ADMIN");
        userInfo.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(userInfo);
    }


}
