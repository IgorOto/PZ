package com.igi.kinoteka_api.controller;

import com.igi.kinoteka_api.model.UserDto;
import com.igi.kinoteka_api.service.UserService;
import com.igi.kinoteka_api.model.dto.PrincipalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user")
    public void addUser(@RequestBody UserDto user){
        userService.addUser(user);

    }

    @GetMapping("/api/auth")
    public PrincipalDto test(Principal principal) {
        if (principal == null) throw new UsernameNotFoundException("How did you get here?");
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        String username = ((User) token.getPrincipal()).getUsername();
        long id = userService.getByUsername(username)
                .getId();
        return PrincipalDto.builder()
                .id(id)
                .username(username)
                .roleName(token.getAuthorities().iterator().next().getAuthority())
                .build();
    }

}
