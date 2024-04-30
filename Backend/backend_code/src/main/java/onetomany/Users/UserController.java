package onetomany.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import onetomany.AdminActivityReport.adminActivityReport;
import onetomany.AdminActivityReport.adminActivityReportRepository;
import onetomany.WebSocketAdminNot.Message;
import onetomany.WebSocketAdminNot.MessageRepository;
import onetomany.WebSocketAdminNot.adminChat;
import onetomany.adminUser.adminUser;
import onetomany.adminUser.adminUserRepository;
import org.springframework.http.ResponseEntity;

import onetomany.Matches.MatchesRepository;
import onetomany.Reports.Reports;
import onetomany.Reports.ReportsRepository;
import onetomany.hobbies.Hobbies;
import onetomany.hobbies.HobbiesRepository;
import onetomany.hobbies.HobbyType;
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

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    adminChat adminChatt;

    @Autowired
    adminUserRepository adminUserRepository;

    @Autowired
    adminActivityReportRepository adminActivityReportRepository;


    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsersss(){
        for (User useer:userRepository.findAll()) {
            useer.setLastLoggin();
            userRepository.save(useer);
        }
        return userRepository.findAll();
    }
    @GetMapping(path = "/users/{id}")
    User getAllUser(@PathVariable int id){


        return  userRepository.findById(id);
    }

    @GetMapping(path = "/users/u/{username}")
    User getUser (@PathVariable String username){
      return userRepository.findByUsername(username);
    }


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


    @GetMapping(path = "/users/{id}/getFlashcards")
    List<User> getMatches(@PathVariable int id){
      User temp=  userRepository.findById(id);

      return temp.getMatches();
    }
    @GetMapping(path = "/users/getReport/{id}")
    List<Reports> getReports(@PathVariable int id){
        User temp= userRepository.findById(id);

        return temp.getReports();
    }
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

    @PostMapping(path = "/users/{id}/addHobbie/")
    String addHobbie(@PathVariable int id,@RequestBody Hobbies hobbie){
      User temp=   userRepository.findById(id);


     Hobbies temp2= hobbiesRepository.findByName(hobbie.getName());
        temp2.addUser(temp);
        hobbiesRepository.save(temp2);
        temp.addHobbie(hobbiesRepository.findByName(hobbie.getName()));
        userRepository.save(temp);
        hobbiesRepository.save(temp2);
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
          tempUser.addReport(reportsRepository.findByReport(report.getReport()));
          userRepository.save(tempUser);
          adminUser temp= null;
        for (adminUser admin: adminUserRepository.findAll()) {
                if (temp==null || temp.getAdminActivityReportList().size() > admin.getAdminActivityReportList().size() ){
                    temp= admin;
                }
        }
        int reportId= reportsRepository.findByReport(report.getReport()).getId();
        adminActivityReport adminrep= new adminActivityReport(temp.getEmailId(),temp.getUsername(),reportId,"User has made a new report");

        adminActivityReportRepository.save(adminrep);
        temp.addAminActivityReport(adminActivityReportRepository.findByReportID(reportId));
        adminUserRepository.save(temp);
        adminChatt.broadcast(tempUser.getUsername() +"has made a new report on user " + tempUser2.getUsername());
        adminChatt.sendMessageToPArticularUser(temp.getUsername(),"You have been assigned a new Admin assignment, with user report" + reportId);
            return success;
    }

    @DeleteMapping(path = "/users/{id}/removeHobbie")
    String deleteHobbie(@PathVariable int id, @RequestBody Hobbies hoobie){
        User tempUser= userRepository.findById(id);
        Hobbies hobbieTemp= hobbiesRepository.findByName(hoobie.getName());
        if(tempUser==null || hobbieTemp==null || !tempUser.getHobbies().contains(hobbieTemp))
            return failure;
        hobbieTemp.removeUser(tempUser);
        tempUser.removeHobbie(hobbieTemp);
        hobbiesRepository.save(hobbieTemp);
        userRepository.save(tempUser);
        return success;
    }



//    @DeleteMapping(path = "/users/{id}")
//    String deleteUser(@PathVariable long id){
//
//        userRepository.deleteById(id);
//        //userRepository.deleteById(temp.getId());
//
//        return success;
//    }

}

