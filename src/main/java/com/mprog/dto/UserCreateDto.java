package com.mprog.dto;

import com.mprog.entity.PersonalInfo;
import com.mprog.entity.Role;
import com.mprog.validation.UpdateCheck;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record UserCreateDto(
        @Valid
        PersonalInfo personalInfo,
        @NotNull
        String username,
        String info,
        @NotNull(groups = UpdateCheck.class)
        Role role,
        Integer companyId) {
}
