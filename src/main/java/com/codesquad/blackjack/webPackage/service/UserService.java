package com.codesquad.blackjack.webPackage.service;

import com.codesquad.blackjack.webPackage.domain.User;
import com.codesquad.blackjack.webPackage.domain.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    public User add(User user) {
        return userRepository.save(user);
    }

    public User login(String userId, String password) throws Exception{
        return userRepository.findByUserId(userId)
                .filter(user -> user.matchPassword(password))
                .orElseThrow(Exception::new);
    }
}
