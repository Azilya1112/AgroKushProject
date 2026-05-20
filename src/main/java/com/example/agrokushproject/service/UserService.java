package com.example.agrokushproject.service;

import com.example.agrokushproject.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto getById(Long id);
    UserDto getByEmail(String email);
    Page<UserDto> getAll(Pageable pageable);
    UserDto update(UserDto dto);
    void delete(Long id);
}
