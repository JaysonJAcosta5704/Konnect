package onetomany.Users;

import java.util.List;

import onetomany.Matches.MatchesRepository;
import onetomany.Reports.Reports;
import onetomany.Reports.ReportsRepository;
import onetomany.hobbies.HobbiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReportsRepository reportsRepository;

    @Autowired
    HobbiesRepository hobbiesRepository;

    @Autowired
    MatchesRepository matchesRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        for (User useer:userRepository.findAll()) {
            useer.setLastLoggin();
        }
        return userRepository.findAll();
    }


    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }




    @PostMapping(path = "/users/")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        userRepository.save(user);
        return success;
    }

    @PutMapping("/users/{id}/")
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);
        if(user == null)
            return null;

        userRepository.save(request);
        return userRepository.findById(id);
    }

    @PostMapping("/users/addReport/{id}/")
        String adduserReport(@PathVariable int id,@RequestBody Reports report ){
            User tempUser= userRepository.findById(id);
            if(tempUser == null || report ==null)
                return null;
            report.setUser(tempUser);
          reportsRepository.save(report);
          tempUser.addReport(reportsRepository.findById(1));
          userRepository.save(tempUser);
            return success;
    }
    


    @DeleteMapping(path = "/users/{id}")
    String deleteLaptop(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }


    @PutMapping("/users/{userId}/Reports/{reportId}")
    String assignLaptopToUser(@PathVariable int userId,@PathVariable int reportId){
        User user = userRepository.findById(userId);
        Reports report = reportsRepository.findById(reportId);
        if(user == null || report == null)
            return failure;
        report.setUser(user);
        user.setUserReports(report);
        userRepository.save(user);
        return success;
    }
}
