package pl.CarRally.carrally.Authentication;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwt")
class JwtConfigurationProperties {
    private String secret;
    private int expirationTimeMs;

    public String getSecret() {
        return secret;
    }

    void setSecret(String secret) {
        this.secret = secret;
    }

    public int getExpirationTimeMs() {
        return expirationTimeMs;
    }

    void setExpirationTimeMs(int expirationTimeMs) {
        this.expirationTimeMs = expirationTimeMs;
    }
}
