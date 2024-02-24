package onetomany;

import java.util.Date;

import onetomany.Matches.MatchesRepository;
import onetomany.Reports.Reports;
import onetomany.Reports.ReportsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import onetomany.Users.User;
import onetomany.Users.UserRepository;

@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create 3 users with their machines and phones
    @Bean
    CommandLineRunner initUser(UserRepository userRepository, MatchesRepository matchesRepository, ReportsRepository reportsRepository) {
        return args -> {
            User user1 = new User("Daniel", "dmvp01@somemail.com", new Date());
            User user2 = new User("Nishi", "Nishi@somemail.com", new Date());
            User user3 = new User("Jason", "Jason@somemail.com", new Date());


             userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        };
    }

}