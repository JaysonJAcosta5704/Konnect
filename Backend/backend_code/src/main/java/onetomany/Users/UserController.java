package onetomany.Users;

import java.util.ArrayList;
import java.util.List;

import onetomany.Matches.MatchesRepository;
import onetomany.Reports.Reports;
import onetomany.Reports.ReportsRepository;
import onetomany.hobbies.HobbiesRepository;
import onetomany.userLogIn.userLogin;
import onetomany.userLogIn.userLoginRepository;
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

    @Autowired
    userLoginRepository userLoginRepository;

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


    @GetMapping("/users/getReports/{id}/")
    List<Reports> add(@PathVariable int id){
        User tempUser= userRepository.findById(id);
        if(tempUser == null)
            return null;
        return tempUser.getReports();
    }

    @PostMapping(path = "/users/")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        userRepository.save(user);
        userLogin temp= new userLogin(user.getUsername(),user.getEmailId(),'n',user.getUserPassword());
        userLoginRepository.save(temp);
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
    String deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }

}
