package com.group6.careu.service;

import com.group6.careu.entity.User;
import com.group6.careu.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    public List<User> listAll();
    public void save(User user);
    public void encodePassword(User user);
    public boolean isEmailUnique(String email);
    public void updateUserEnabledStatus(Integer id, boolean status);
    public void delete(Integer id) throws UserNotFoundException;

}
