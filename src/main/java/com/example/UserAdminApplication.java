package com.example;

import com.example.model.AppRole;
import com.example.model.AppUser;
import com.example.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class UserAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAdminApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AccountService accountService){
            return args -> {
              accountService.saveRole(new AppRole(null,"USER"));
              accountService.saveRole(new AppRole(null,"ADMIN"));
                Stream.of("user1","user2","user3","admin").forEach(user->accountService
                        .saveUser(user,"1234","1234"));
                        accountService.addRoleToUser("admin","ADMIN");
            };
    }
    @Bean
    BCryptPasswordEncoder getBCrypt(){
        return new BCryptPasswordEncoder();
    }
}

