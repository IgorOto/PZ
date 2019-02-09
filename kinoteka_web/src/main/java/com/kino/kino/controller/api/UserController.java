package com.kino.kino.controller.api;

import com.kino.kino.model.User;
import com.kino.kino.model.dto.UserGetAuthDto;
import com.kino.kino.model.dto.UserPostAuthDto;
import com.kino.kino.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public UserGetAuthDto get(Principal principal) {
        return modelMapper.map(userService
                .getUserByUsername(principal
                        .getName()
                ), UserGetAuthDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(UserPostAuthDto userDto){
        User user = modelMapper.map(userDto, User.class);
        userService.add(user);
    }

}
