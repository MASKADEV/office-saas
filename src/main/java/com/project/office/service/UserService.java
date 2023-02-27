package com.project.office.service;

import com.project.office.dto.request.RegistrationRequest;
import com.project.office.dto.request.Role;
import com.project.office.dto.request.Signin;
import com.project.office.entity.User;
import com.project.office.repository.UserRepository;
import com.project.office.util.authority.CustomGrantedAuthority;
import com.project.office.util.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private int size;

    public void saveUser(RegistrationRequest registrationRequest) {
        try {
        User user = new User();
            user.setFull_name(registrationRequest.getFull_name());
            user.setUsername(registrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setEmail(registrationRequest.getEmail());
            user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        }catch(Exception e){
            log.info(e.getMessage());
        }
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    public String signin(Signin signin) {
        User user = userRepository.findByEmail(signin.getEmail()).orElse(null);
        if(user != null && passwordEncoder.matches(signin.getPassword(), user.getPassword())){
            return  jwtTokenUtil.generateToken(this.loadUserByUsername(user.getUsername()));
        }
        return "";
    }
    public List<User> getusers(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Collection<CustomGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> {
                        return new CustomGrantedAuthority(role.name());
                })
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
