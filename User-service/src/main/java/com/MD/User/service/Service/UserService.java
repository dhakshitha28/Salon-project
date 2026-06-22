package com.MD.User.service.Service;

import com.MD.User.service.exception.UserException;
import com.MD.User.service.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    void deleteUser(Long userId) throws UserException;
    User getUserById(Long id) throws UserException;

    User updateUser(User user,Long userId) throws UserException;
}
