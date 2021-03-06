package com.team5.c4quanlyphongsach.controller.jwt;

import com.team5.c4quanlyphongsach.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.team5.c4quanlyphongsach.service.userService.JwtUsersDetailsService;


import com.team5.c4quanlyphongsach.config.JwtTokenUtil;
import com.team5.c4quanlyphongsach.model.security.JwtRequest;
import com.team5.c4quanlyphongsach.model.security.JwtResponse;

@RestController
@CrossOrigin("*")
public class JwtAuthenticationController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUsersDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        String email = userDetails.getUsername();
        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(token,email,customerService.findByEmail(email).get().getId());
        return ResponseEntity.ok(jwtResponse);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
