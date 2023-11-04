package skrm.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public OkHttpClient createOkHttpClient() {
        return new OkHttpClient();
    }
}
