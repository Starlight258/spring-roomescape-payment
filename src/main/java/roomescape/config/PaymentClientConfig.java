package roomescape.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class PaymentClientConfig {

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl("https://api.tosspayments.com")
                .build();
    }
}
