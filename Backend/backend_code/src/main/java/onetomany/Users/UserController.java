package onetomany.Users;

import java.util.ArrayList;
import java.util.Date;
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

//    @GetMapping(path = "/users")
//    List<User> getAllUsers(){
//        for (User useer:userRepository.findAll()) {
//            useer.setLastLoggin();
//        }
//        return userRepository.findAll();
//    }


//    @GetMapping(path = "/loginEmail/{email}/{password}/")
//    User getUserById( @PathVariable String email, @PathVariable String password){
//        User temp= userRepository.findByEmailId(email);
//        if (temp.getUserPassword().equals(password))
//            return temp;
//        return null;
//    }
//    @GetMapping(path = "/us/{username}/{password}/")
//    User getUserByUsername( @PathVariable String username, @PathVariable String password){
//        User temp= userRepository.findByUsername(username);
//        if (temp.getUserPassword().equals(password))
//            return temp;
//        return null;
//    }


//    @GetMapping("/users/getReports/{id}/")
//    List<Reports> add(@PathVariable int id){
//        User tempUser= userRepository.findById(id);
//        if(tempUser == null)
//            return null;
//        return tempUser.getReports();
//    }

    @PostMapping(path = "/users/")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        user.setJoiningDate(new Date());
        user.setLastLoggin();
        user.setIfActive(true);
        userRepository.save(user);

        User temptest= userRepository.findUserByUsername(user.getUsername());
        userLogin temp= new userLogin(user.getUsername(),user.getEmailId(),'n',user.getUserPassword());
        temp.setUser(temptest);
        temptest.setUserLogin(temp);
        userLoginRepository.save(temp);
        userRepository.save(user);
        return success;
    }

    @PutMapping("/users/{id}/{password}")
    User updateUser(@PathVariable int id, @PathVariable String password, @RequestBody User request){
        User user = userRepository.findById(id);

        if(user == null || !user.getUserPassword().equals(password))
            return null;
        userRepository.save(request);
        return userRepository.findById(id);
    }

    @PostMapping("/users/addReport/{id}/{id2}/")
        String adduserReport(@PathVariable int id, @PathVariable int id2,@RequestBody Reports report ){
            User tempUser= userRepository.findById(id);
            User tempUser2= userRepository.findById(id2);
            if(tempUser == null || report ==null)
                return null;
            report.setUser1(tempUser);
            report.setUser2(tempUser2);
          reportsRepository.save(report);
          tempUser.addReport(reportsRepository.findById(1));
          userRepository.save(tempUser);
            return success;
    }
    


    @DeleteMapping(path = "/users/{email}/{password}")
    String deleteUser(@PathVariable String email, @PathVariable String password){
        User temp= userRepository.findByEmailId(email);
        if (!temp.getUserPassword().equals(password))
            return failure;
        userRepository.delete(temp);
        //userRepository.deleteById(temp.getId());

        return success;
    }

}
