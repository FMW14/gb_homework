package com.vtb.javacourses.lesson21.services;

import com.vtb.javacourses.lesson21.entities.User;
import com.vtb.javacourses.lesson21.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;

    public void save(User user) {
        userRepo.save(user);
    }

    public User getById(Long id) {
        return userRepo.getById(id);
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }
}
