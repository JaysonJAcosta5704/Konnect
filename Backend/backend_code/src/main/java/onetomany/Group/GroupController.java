package onetomany.Group;

import onetomany.Users.User;
import onetomany.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping(path = "/getGroups")
    public List<Group> getGroup(){
        return groupRepository.findAll();
    }
    @PostMapping(path = "/create/{groupName}")
    public ResponseEntity<String> createGroup(@PathVariable String groupName) {
        Group group = new Group(groupName);
        groupRepository.save(group);
        return ResponseEntity.ok("{\"message\":\"Group created successfully\"}");
    }

    @PostMapping(path = "/group/add-user/{id}/{username}")
    public ResponseEntity<String> addUserToGroup(@PathVariable int id, @PathVariable String username) {
        Group tempGroup = groupRepository.findById(id);
        User tmepUser= userRepository.findByUsername(username);
        if ( tempGroup==null || tmepUser == null) {
            return ResponseEntity.badRequest().body("{\"message\":\"Group or user not found\"}");
        }
        tempGroup.addUsers(tmepUser);
        groupRepository.save(tempGroup);
        tmepUser.addGroup(groupRepository.findById(id));
        userRepository.save(tmepUser);

        return ResponseEntity.ok("{\"message\":\"User added to group successfully\"}");
    }

    @GetMapping(path = "/list/{username}")
    public List<Group> listGroups(@PathVariable String username) {
        List<Group> gropusRet= new ArrayList<>();
        User temp= userRepository.findByUsername(username);
        if(temp==null)
            return null;
        for (Group group: groupRepository.findAll()){
            if(group.getUsers().contains(temp))
                gropusRet.add(group);
        }
        return gropusRet;
    }

}