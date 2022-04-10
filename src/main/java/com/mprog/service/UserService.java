package com.mprog.service;

import com.mprog.dao.UserRepository;
import com.mprog.dto.UserReadDto;
import com.mprog.entity.User;
import com.mprog.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;


    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::mapFrom);
    }

    public boolean delete(Long id) {
        Optional<User> maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(user.getId()));
        return maybeUser.isPresent();
    }
}
