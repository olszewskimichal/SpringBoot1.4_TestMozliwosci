package com.register.example.configuration;

import com.register.example.builders.UserBuilder;
import com.register.example.builders.VerificationTokenBuilder;
import com.register.example.entity.Product;
import com.register.example.entity.Role;
import com.register.example.entity.User;
import com.register.example.entity.test.Dupa;
import com.register.example.entity.test.Test;
import com.register.example.entity.tokens.PasswordResetToken;
import com.register.example.repository.PasswordResetTokenRepository;
import com.register.example.repository.ProductRepository;
import com.register.example.repository.UserRepository;
import com.register.example.repository.VerificationTokenRepository;
import com.register.example.repository.test.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
@Profile("!test")
public class DevDBConfig {
    private static final String ADMINPASS = "admin";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository resetTokenRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ProductRepository repository;

    @PostConstruct
    public void populateDatabase() {
        log.info("ładowanie bazy testowej");
        userRepository.save(
                new UserBuilder("admin@o2.pl", "admin@o2.pl").withPassword(ADMINPASS)
                        .withRole(Role.ADMIN)
                        .withEnabled(true)
                        .build());
        User user = userRepository.save(
                new UserBuilder("user@poczta.pl", "user").withPassword("user").withEnabled(true).build());
        userRepository.save(
                new UserBuilder("user2@poczta.pl", "user2").withPassword("user").withEnabled(false).build());
        userRepository.save(new UserBuilder("admin1", "admin8").withPassword(ADMINPASS)
                .withRole(Role.ADMIN)
                .withEnabled(true)
                .build());
        userRepository.save(new UserBuilder("admin2", "admin7").withPassword(ADMINPASS)
                .withRole(Role.ADMIN)
                .withEnabled(true)
                .build());
        userRepository.save(new UserBuilder("admin3", "admin6").withPassword(ADMINPASS)
                .withRole(Role.ADMIN)
                .withEnabled(true)
                .build());
        userRepository.save(new UserBuilder("admin4", "admin5").withPassword(ADMINPASS)
                .withRole(Role.ADMIN)
                .withEnabled(true)
                .build());

        verificationTokenRepository.save(new VerificationTokenBuilder(user, true).build());
        verificationTokenRepository.save(
                new VerificationTokenBuilder(user, false).withDate(LocalDateTime.now().minusDays(8)).build());
        verificationTokenRepository.save(
                new VerificationTokenBuilder(user, false).withDate(LocalDateTime.now().minusDays(6)).build());
        resetTokenRepository.save(new PasswordResetToken("testowyToken", user, Boolean.FALSE));
        resetTokenRepository.save(new PasswordResetToken("testowyToken2", user, Boolean.TRUE));
        resetTokenRepository.save(new PasswordResetToken("testowyToken3", user, Boolean.FALSE));
        resetTokenRepository.save(new PasswordResetToken("testowyToken4", user, Boolean.FALSE));
        resetTokenRepository.save(new PasswordResetToken("testowyToken5", user, Boolean.FALSE));

        repository.save(new Product("1Snake", "", "", BigDecimal.TEN));
        repository.save(new Product("2Snake", "", "", BigDecimal.TEN));
        repository.save(new Product("3Snake", "", "", BigDecimal.TEN));
        repository.save(new Product("4Snake", "", "", BigDecimal.TEN));
        repository.save(new Product("5Snake", "", "", BigDecimal.TEN));
        repository.save(new Product("6Snake", "", "", BigDecimal.TEN));

        Test test = new Test();
        Dupa dupa = new Dupa("test", test);
        Dupa dupa1 = new Dupa("test2", test);
        Dupa dupa2 = new Dupa("test3", test);
        Set dupas = new HashSet<Dupa>();
        dupas.addAll(Arrays.asList(dupa, dupa1, dupa2));


        test.setDupas(dupas);
        testRepository.save(test);
    }
}
