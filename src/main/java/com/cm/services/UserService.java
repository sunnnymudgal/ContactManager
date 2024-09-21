package com.cm.services;

import com.cm.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {
    User saveUser(User user);
    Optional<User> getUserbyId(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String userId);
    boolean isuserExistsByEmail(String userId);
    List<User> getAllUsers();

}
