package com.team5.c4quanlyphongsach.service.userService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.team5.c4quanlyphongsach.model.Customer;
import com.team5.c4quanlyphongsach.model.users.Users;
import com.team5.c4quanlyphongsach.repository.IUsersRepository;
import com.team5.c4quanlyphongsach.repository.customer.ICustomerRepository;
import com.team5.c4quanlyphongsach.service.customer.ICustomerService;
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

    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public Users getUserByUsername(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findCustomerByEmail(username);
        Users user = new Users(customer.get().getEmail(),customer.get().getPassword(),customer.get().getRoles());
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