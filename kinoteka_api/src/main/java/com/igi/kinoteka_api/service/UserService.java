package com.igi.kinoteka_api.service;

import com.igi.kinoteka_api.model.UserDto;
import com.igi.kinoteka_api.model.User;

public interface UserService {
    User getByUsername(String username);

    void addUser(UserDto user);
}
