package com.sosbicho;

import com.sosbicho.domain.Bicho;
import com.sosbicho.domain.User;
import com.sosbicho.repository.BichoRepository;
import com.sosbicho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BichoRepository bichoRepository;

    @Override
    public void run(String... strings) {
        User userAdmin = new User("admin", "admin");
        User userTest = new User("test", "test");
        userRepository.save(userAdmin);
        userRepository.save(userTest);

        Bicho bicho = new Bicho();
        bicho.setId(1L);
        bicho.setName("BName");
        bicho.setSize("BGrande");
        bicho.setSpecies("Dog");
        bicho.setRace("Mutt");
        bicho.setAge(5);
        bicho.setAdopted(false);
        bicho.setOwner(userAdmin);
        bichoRepository.save(bicho);

        bicho = new Bicho();
        bicho.setId(2L);
        bicho.setName("CName");
        bicho.setSize("Small");
        bicho.setSpecies("Cat");
        bicho.setRace(null);
        bicho.setAge(12);
        bicho.setOwner(userTest);
        bicho.setAdopted(true);
        bicho.setInterested(Arrays.asList(userAdmin, userTest));
        bichoRepository.save(bicho);
    }

}
