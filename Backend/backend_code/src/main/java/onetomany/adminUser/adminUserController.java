package onetomany.adminUser;

import java.util.ArrayList;
import java.util.List;

import onetomany.Matches.MatchesRepository;
import onetomany.Reports.Reports;
import onetomany.Reports.ReportsRepository;
import onetomany.Users.User;
import onetomany.Users.UserRepository;
import onetomany.adminUser.adminUser;
import onetomany.adminUser.adminUserRepository;
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
public class adminUserController {

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

    @Autowired
    adminUserRepository adminUserRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/adminUser/{email}/{password}/")
    List<adminUser> getAllUsers(@PathVariable String email, @PathVariable String password){
        adminUser temp= adminUserRepository.findByEmailId(email);
       if (temp == null)
           return null;
       if (temp.getAdminPassword() != password)
           return null;
       return adminUserRepository.findAll();
    }

    @GetMapping(path = "/adminUser/")
    List<adminUser> getAllUsers(){

        return adminUserRepository.findAll();
    }


    @GetMapping(path = "/adminUser/{email}/{password}/{id}")
    adminUser getAdminById( @PathVariable int id){
        return adminUserRepository.findById(id);
    }

    @GetMapping(path = "/adminUser/getUsers/{email}/{password}/")
    List<User> getUsers(@PathVariable String email, @PathVariable String password){
        adminUser temp= adminUserRepository.findByEmailId(email);
        if (temp == null)
            return null;
        if (!temp.getAdminPassword().equals(password))
            return null;
        return userRepository.findAll();
    }


    @GetMapping("/adminUser/getUser/{email}/{password}/{id}/")
    User getUserbyId(@PathVariable String email, @PathVariable String password, @PathVariable int id){
        adminUser temp= adminUserRepository.findByEmailId(email);
        if (temp == null)
            return null;
        if (!temp.getAdminPassword().equals(password))
            return null;
        return userRepository.findById(id);
    }
    @GetMapping("/adminUser/getReports/{email}/{password}/")
    List<Reports> getAllReports(@PathVariable String email, @PathVariable String password){
        adminUser temp= adminUserRepository.findByEmailId(email);
        if (temp == null)
            return null;
        if (!temp.getAdminPassword().equals(password))
            return null;
        return reportsRepository.findAll();
    }

    @GetMapping("/adminUser/getReports/{email}/{password}/{id}")
    Reports getAllReports(@PathVariable String email, @PathVariable String password, @PathVariable int id){
        adminUser temp= adminUserRepository.findByEmailId(email);
        if (temp == null)
            return null;
        if (!temp.getAdminPassword().equals(password))
            return null;
        return reportsRepository.findById(id);
    }

    @PostMapping(path = "/adminUser/{adminPassword}/")
    String createUser(@RequestBody adminUser user){
        if (user == null)
            return failure;

        adminUserRepository.save(user);
        adminUser temptest= adminUserRepository.findByEmailId(user.getEmailId());
        userLogin temp= new userLogin(user.getUsername(),user.getEmailId(),'A', temptest.getAdminPassword());
        temptest.setUserLogin(temp);
        temp.setAdminUser(temptest);
        userLoginRepository.save(temp);
        adminUserRepository.save(user);
        return success;
    }

    @PostMapping(path = "/adminUser/")
    String createNewAdmin(@RequestBody adminUser user){
        if (user == null)
            return failure;
        adminUserRepository.save(user);
        adminUser temptest= adminUserRepository.findByEmailId(user.getEmailId());
        userLogin temp= new userLogin(user.getUsername(),user.getEmailId(),'A', temptest.getAdminPassword());
        temptest.setUserLogin(temp);
        temp.setAdminUser(temptest);
        userLoginRepository.save(temp);
        adminUserRepository.save(user);
        return success;
    }
}
