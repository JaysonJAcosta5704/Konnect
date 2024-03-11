package onetomany.userLogIn;

import java.util.ArrayList;
import java.util.List;

import onetomany.Matches.MatchesRepository;
import onetomany.Reports.Reports;
import onetomany.Reports.ReportsRepository;
import onetomany.Users.UserRepository;
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
public class userLoginController {

    @Autowired
    userLoginRepository userLoginRepository;

    @Autowired
    UserRepository userRepository;


    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/usersLogin/")
    List<userLogin> getAllUsers(){

        return userLoginRepository.findAll();
    }


    @GetMapping(path = "/usersLogin/{id}")
    userLogin getUserById( @PathVariable int id){
        for (userLogin useer:userLoginRepository.findAll()) {
            useer.getUser().setLastLoggin();
        }
        return userLoginRepository.findById(id);
    }


    @PostMapping(path = "/usersLogin/{type}")
    String createUser(@PathVariable char type,@RequestBody userLogin user){
        if (user == null)
            return failure;
        userLoginRepository.save(user);
        return success;
    }








}
