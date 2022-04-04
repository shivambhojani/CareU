package com.group6.careu.service;

import com.group6.careu.entity.User;
import com.group6.careu.exceptions.UserNotFoundException;
import com.group6.careu.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean save(User user) {
        user.setEnabled(true);
        encodePassword(user);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
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
    public boolean updateUserEnabledStatus(Integer id, boolean enabled) {
        try {
            userRepository.updateEnabledStatus(id, enabled);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void delete(Integer id) throws UserNotFoundException {
        Long countById = userRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public String updateResetPasswordToken(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String token = RandomString.make(30);
            user.setResetPasswordToken(token);
            userRepository.save(user);

            return token;
        } else {
            throw new UserNotFoundException("Could not find any user with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(String token, String newPassword) throws UserNotFoundException {
        User user = userRepository.findByResetPasswordToken(token);
        if (user == null) {
            throw new UserNotFoundException("No customer found: invalid token");
        }
        user.setPassword(newPassword);
        user.setResetPasswordToken(null);
        encodePassword(user);

        userRepository.save(user);
    }
}
