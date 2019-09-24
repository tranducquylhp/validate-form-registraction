package com.codegym.repository;

import com.codegym.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    void save(User user);
}
