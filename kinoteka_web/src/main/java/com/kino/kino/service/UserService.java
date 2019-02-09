package com.kino.kino.service;

import com.kino.kino.model.User;

public interface UserService {
    User getUserByUsername(final String username);

    void add(User user);
}
