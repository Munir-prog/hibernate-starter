package com.mprog.service;

import com.mprog.dao.UserRepository;
import com.mprog.dto.UserReadDto;
import com.mprog.entity.User;
import com.mprog.mapper.Mapper;
import com.mprog.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;

    public <T> Optional<T> findById(Long id, Mapper<User, T> mapper) {
        Map<String, Object> properties = Map.of(
                GraphSemantic.LOAD.getJpaHintName(), userRepository.getEntityManager().getEntityGraph("withCompany")
        );
        return userRepository.findById(id, properties)
                .map(mapper::mapFrom);
    }

    public Optional<UserReadDto> findById(Long id) {
        Map<String, Object> properties = Map.of(
                GraphSemantic.LOAD.getJpaHintName(), userRepository.getEntityManager().getEntityGraph("withCompany")
        );
        return userRepository.findById(id, properties)
                .map(userReadMapper::mapFrom);
    }

    public boolean delete(Long id) {
        Optional<User> maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(user.getId()));
        return maybeUser.isPresent();
    }
}
