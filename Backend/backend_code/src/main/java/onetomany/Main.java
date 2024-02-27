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
import onetomany.hobbies.Hobbies;
import onetomany.hobbies.HobbiesRepository;

@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create 3 users with their machines and phones
    @Bean
    CommandLineRunner initUser(UserRepository userRepository, MatchesRepository matchesRepository, ReportsRepository reportsRepository,HobbiesRepository hobbiesRepository) {
        return args -> {
            User user1 = new User("Daniel", "dmvp01@somemail.com", new Date(), "Daniel123");
            User user2 = new User("Nishi", "Nishi@somemail.com", new Date(),"Nishi456");
            User user3 = new User("Jayson", "Jayson@somemail.com", new Date(),"Jayson");



            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            Hobbies hobby1 = new Hobbies("Reading", user1);
            Hobbies hobby2 = new Hobbies("Gaming", user2);
            Hobbies hobby3 = new Hobbies("Hiking", user3);

            hobbiesRepository.save(hobby1);
            hobbiesRepository.save(hobby2);
            hobbiesRepository.save(hobby3);


            reportsRepository.save(new Reports("Bad photo"));
            reportsRepository.save(new Reports("Weird Bio"));
            reportsRepository.save(new Reports("Agressive"));
            reportsRepository.save(new Reports("Rude"));
            Reports r1 = reportsRepository.findById(1);
            Reports r2 = reportsRepository.findById(2);
            Reports r3 = reportsRepository.findById(3);
            Reports r4= reportsRepository.findById(4);




            r1.setUser(user1);
            r2.setUser(user1);
            r3.setUser(user2);
            r4.setUser(user3);

            reportsRepository.save(r1);
            reportsRepository.save(r2);
            reportsRepository.save(r3);
            reportsRepository.save(r4);
            user1.addReport(r1);
            user1.addReport(r2);
            user2.addReport(r3);
            user3.addReport(r4);


            System.out.println(user1.getReports());




//            userRepository.save(user1);
//            userRepository.save(user2);
//            userRepository.save(user3);
        };
    }

}