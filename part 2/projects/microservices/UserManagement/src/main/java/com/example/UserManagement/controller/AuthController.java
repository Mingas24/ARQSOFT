package com.example.UserManagement.controller;

import com.example.UserManagement.dto.UserDTO;
import com.example.UserManagement.security.jwt.AuthRequest;
import com.example.UserManagement.security.jwt.AuthResponse;
import com.example.UserManagement.security.jwt.JwtTokenUtil;
import com.example.UserManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;


@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private IUserService userService;

    @MutationMapping
    public AuthResponse createAuthenticationToken(@Argument AuthRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = this.userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final UserDTO userDTO = (UserDTO) this.userService.getUserByUsername(authenticationRequest.getUsername()).getObject();

        final String token = this.jwtTokenUtil.generateToken(userDetails);

        return new AuthResponse(token, userDTO);
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
