package com.ecommerce.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
@Getter
@Setter
@NoArgsConstructor
public class KeycloakAdminProps {
    private String serverUrl;
    private String realm;
    private String grantType;
    private String clientId;
    private String clientSecret;

}
