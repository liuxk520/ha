package cn.liuxiaokang.heroesapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "c")
public class Config implements Serializable {


    private Cors cors = new Cors();

    private Jwt jwt = new Jwt();



    @Data
    public static class Cors {
        private List<String> allowedOrigins = new ArrayList<>();

        private List<String> allowedMethods = new ArrayList<>();

        private List<String> allowedHeaders = new ArrayList<>();
    }

    @Data
    public static class Jwt {
        private String header;

        private String secret;

        private Long expiration;

        private String issuer;

        private String authenticationPath;
    }
}
