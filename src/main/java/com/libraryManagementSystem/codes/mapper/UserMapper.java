package com.libraryManagementSystem.codes.mapper;

import com.libraryManagementSystem.codes.dto.UserDTO;
import com.libraryManagementSystem.codes.entity.User;

public class UserMapper {


    // ✅ Convert Entity → DTO
    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .accountNonLocked(user.isAccountNonLocked())
                .membershipDate(user.getMembershipDate())
                .build();
    }

    // ✅ Convert DTO → Entity
    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .role(dto.getRole())
                .enabled(dto.isEnabled())
                .accountNonLocked(dto.isAccountNonLocked())
                .membershipDate(dto.getMembershipDate())
                // ⚠️ Password intentionally skipped for security
                .build();
    }


}
