package com.autoparts.marketplace.config;

import com.autoparts.marketplace.entity.Role;
import com.autoparts.marketplace.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!roleRepository.findByName("SELLER").isPresent()) {
            Role sellerRole = new Role();
            sellerRole.setName("SELLER");
            roleRepository.save(sellerRole);
        }
    }
}