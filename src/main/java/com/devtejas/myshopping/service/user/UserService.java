package com.devtejas.myshopping.service.user;


import com.devtejas.myshopping.dto.UserDto;
import com.devtejas.myshopping.exception.AlreadyExistsException;
import com.devtejas.myshopping.exception.ResourceNotFoundException;
import com.devtejas.myshopping.repository.UserRepository;
import com.devtejas.myshopping.request.CreateUserRequest;
import com.devtejas.myshopping.request.UserUpdateRequest;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

//    @Override
//    public User createUser(CreateUserRequest request) {
//        return Optional.of(request)
//                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
//                .map(req -> {
//                    User user = new User();
//                    user.setEmail(request.getEmail());
//                    user.setPassword(request.getPassword());
//                    user.setFirstName(request.getFirstName());
//                    user.setLastName(request.getLastName());
//                    return userRepository.save(user);
//                }) .orElseThrow(() -> new AlreadyExistsException("Oops!" +request.getEmail() +" already exists!"));
//    }

//    @Override
//    public User updateUser(UserUpdateRequest request, Long userId) {
//        if (request == null) {
//            throw new IllegalArgumentException("UserUpdateRequest cannot be null");
//        }
//
//        return userRepository.findById(userId).map(existingUser -> {
//            existingUser.setFirstName(request.getFirstName());
//            existingUser.setLastName(request.getLastName());
//            return userRepository.save(existingUser);
//        }).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
//
//    }



    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete,()-> {
            throw new ResourceNotFoundException("User Not Found");
        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

}
