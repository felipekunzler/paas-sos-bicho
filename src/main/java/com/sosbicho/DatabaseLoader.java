package com.sosbicho;

import com.sosbicho.domain.User;
import com.sosbicho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... strings) {
        repository.save(new User("Frodo", "Baggins"));
    }

}
