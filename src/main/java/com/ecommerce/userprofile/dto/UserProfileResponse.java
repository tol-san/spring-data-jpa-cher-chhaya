package com.ecommerce.userprofile;

import lombok.Builder;

@Builder
public record UserProfileResponse(
        String userId,
        String username,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String gender,
        String address
        ) {
}
