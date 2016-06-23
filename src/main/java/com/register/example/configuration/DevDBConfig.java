package com.register.example.configuration;

import com.register.example.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("!prod")
@Slf4j
public class DevDBConfig {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void populateDatabase(){
        log.info("ładowanie bazy testowej");
    }
}
