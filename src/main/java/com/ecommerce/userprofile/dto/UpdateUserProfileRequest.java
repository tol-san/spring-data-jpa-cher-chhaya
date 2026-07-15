package com.ecommerce.userprofile;

import lombok.Builder;

@Builder
public record UpdateUserProfileRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String gender,
        String address
        ) {
}
