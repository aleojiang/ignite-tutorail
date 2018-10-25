package pjiang.tutorial.datajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  11:24 2018/10/24.
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EnableTransactionManagement
public class DataJpaApp {
    private static Logger logger = LoggerFactory.getLogger(DataJpaApp.class);
    
    public static void main(String[] args) {
        SpringApplication.run(DataJpaApp.class, args);
        logger.info("============= SpringBoot Start Success =============");
    }
    
}
