package com.gh.springboot.service;

import com.gh.springboot.model.User;
import com.gh.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User create(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new IllegalStateException("User with this email address already exists");
        }
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User with such ID " + id + " does not exist");
        }
        userRepository.deleteById(id);
    }


    @Transactional
    public void update(Long id, String name, String lastName, String email) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User with such ID " + id + " does not exist");
        }
        User user = optionalUser.get();
        if (email != null && !email.equals(user.getEmail())) {
            Optional<User> optionalUserByEmail = userRepository.findByEmail(email);
            if (optionalUserByEmail.isPresent()) {
                throw new IllegalStateException("User with this email address already exists");
            }
            user.setEmail(email);
        }
        if (name != null && !name.equals(user.getName())) {
            user.setName(name);
        }
        if (lastName != null && !lastName.equals(user.getLastName())) {
            user.setLastName(lastName);
        }
        userRepository.save(user);
    }

    @Transactional
    public User getById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User with such ID " + id + " does not exist");
        }
       return optionalUser.get();
    }
}
