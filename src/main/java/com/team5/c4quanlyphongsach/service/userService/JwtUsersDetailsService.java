package com.team5.c4quanlyphongsach.service.userService;

import java.util.ArrayList;
import java.util.List;

import com.team5.c4quanlyphongsach.model.users.Users;
import com.team5.c4quanlyphongsach.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUsersDetailsService implements IJwtUsersDetailService,UserDetailsService {

    @Autowired
    IUsersRepository userRepository;

    @Override
    public Users getUserByUsername(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = getUserByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());
        String name = user.getName();
        UserDetails userDetails = new User(
                name,
                user.getPassword(),
                authorities
        );

        return userDetails;
    }

}