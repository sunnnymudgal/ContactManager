package com.cm.serviceimpls;

import com.cm.entities.User;
import com.cm.helpers.AppConstants;
import com.cm.helpers.ResourceNotFoundException;
import com.cm.repos.UserRepo;
import com.cm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }


    @Override
    public Optional<User> getUserbyId(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User userToUpdate = userRepo.findById(String.valueOf(user.getUserId())).orElseThrow(()->new ResourceNotFoundException("user not found!!"));
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setAbout(user.getAbout());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        userToUpdate.setProfilePic(user.getProfilePic());
        userToUpdate.setEnabled(user.isEnabled());
        userToUpdate.setEmailVerified(user.isEmailVerified());
        userToUpdate.setPhoneVerified(user.isPhoneVerified());
        userToUpdate.setProvider(user.getProvider());
        userToUpdate.setProviderUserid(user.getProviderUserid());

        User save = userRepo.save(userToUpdate);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User userToDelete = userRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("user not found"));
        userRepo.delete(userToDelete);

    }

    @Override
    public boolean isUserExist(String userId) {
        User userx = userRepo.findById(userId).orElse(null);
        return userx != null;
    }

    @Override
    public boolean isuserExistsByEmail(String email) {
        User emailx = userRepo.findByEmail(email).orElse(null);
        return emailx != null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
