package co.edu.javeriana.mc.survey.init;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
public class DBInit implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(getClass());


    @Override
    @Transactional
    public void run(String... args) throws Exception {
    }
}
