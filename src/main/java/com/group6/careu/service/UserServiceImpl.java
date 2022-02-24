package com.group6.careu.service;

import com.group6.careu.entity.User;
import com.group6.careu.exceptions.UserNotFoundException;
import com.group6.careu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        user.setEnabled(true);
        encodePassword(user);
        userRepository.save(user);
    }
    @Override
    public List<User> listAll(){
        return(List<User>) userRepository.findAll();
    }

    @Override
    public void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    @Override
    public boolean isEmailUnique(String email) {
        User userByEmail = userRepository.getUserByEmail(email);

        return userByEmail == null;
    }
    @Override
    public void updateUserEnabledStatus(Integer id, boolean enabled) {
        userRepository.updateEnabledStatus(id, enabled);
    }

    @Override
    public void delete(Integer id) throws UserNotFoundException {
        Long countById = userRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }

        userRepository.deleteById(id);
    }
}
