package co.edu.javeriana.mc.survey;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Configuration
@PropertySource("classpath:survey.properties")
public class Config {

    @Autowired
    private Environment env;

    public String getProperty(String prop) {
        return env.getProperty(prop);
    }

    @Bean
    DateTimeFormatter createFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    }
}
