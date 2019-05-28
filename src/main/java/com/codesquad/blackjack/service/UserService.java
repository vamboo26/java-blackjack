package com.codesquad.blackjack.service;

import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    public User join(String userId, String password, String name) {
        User newUser = User.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .build();
        return userRepository.save(newUser);
    }

    public User login(String userId, String password) throws Exception {
        return userRepository.findByUserId(userId)
                .filter(user -> user.matchPassword(password))
                .orElseThrow(Exception::new);
    }

    public Iterable<User> findTop10() {
        return userRepository.findTop10ByOrderByChipAmountDesc();
    }

    public long findRankById(User loginUser) {
        checkNotNull(findById(loginUser.getId()));

        return userRepository.countByChipAmountGreaterThanEqual(loginUser.getAmount());
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findById(User loginUser, long id) {
        return userRepository.findById(id)
                .filter(user -> !user.equals(loginUser))
                .orElseThrow(RuntimeException::new);
    }

    public User update(User loginUser, long id, User updatedUser) {
        User original = findById(loginUser, id);
        original.update(loginUser, updatedUser);
        return userRepository.save(original);
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
