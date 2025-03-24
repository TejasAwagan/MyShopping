package com.devtejas.myshopping.service.user;

import com.devtejas.myshopping.dto.UserDto;
import com.devtejas.myshopping.request.CreateUserRequest;
import com.devtejas.myshopping.request.UserUpdateRequest;
import org.apache.catalina.User;

public interface IUserService {

//    User createUser(CreateUserRequest request);

//    User getUserById(Long userId);


//    User updateUser(UserUpdateRequest request,Long userId);

    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

}
