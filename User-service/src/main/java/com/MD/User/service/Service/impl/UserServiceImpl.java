package com.MD.User.service.Service.impl;
import com.MD.User.service.Service.UserService;
import com.MD.User.service.exception.UserException;
import com.MD.User.service.model.User;
import com.MD.User.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) throws UserException {
        Optional<User> otp = userRepository.findById(userId);
           if(otp.isEmpty()){
              throw new UserException("User not found with id " + userId);
        }
         userRepository.deleteById(otp.get().getUserId());

    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> otp = userRepository.findById(id);
        if (otp.isPresent()) {
            return otp.get();
        }
        throw new UserException("User not found");
   }

    @Override
    public User updateUser(User user, Long userId) throws UserException {
        Optional<User> otp = userRepository.findById(userId);
            if (otp.isEmpty()) {
                throw new UserException("User not found with id " + userId);
            }

        User existingUser = otp.get();
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setUserName(user.getUserName());
        return userRepository.save(existingUser);
    }
}

