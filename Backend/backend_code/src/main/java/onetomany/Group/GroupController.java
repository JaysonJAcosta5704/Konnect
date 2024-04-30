package onetomany.Group;

import onetomany.Users.User;
import onetomany.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestParam String groupName) {
        Group group = new Group();
        group.setName(groupName);
        groupRepository.save(group);
        return ResponseEntity.ok("{\"message\":\"Group created successfully\"}");
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUserToGroup(@RequestParam int groupId, @RequestParam String username) {
        Optional<Group> groupOpt = groupRepository.findById(groupId);
        User user = userRepository.findUserByUsername(username);

        if (!groupOpt.isPresent() || user == null) {
            return ResponseEntity.badRequest().body("{\"message\":\"Group or user not found\"}");
        }

        Group group = groupOpt.get();
        group.getUsers().add(user);
        groupRepository.save(group);
        return ResponseEntity.ok("{\"message\":\"User added to group successfully\"}");
    }

    @GetMapping("/list/{username}")
    public ResponseEntity<List<GroupDto>> listGroups(@PathVariable String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Group> groups = groupRepository.findAll();
        List<GroupDto> dtos = groups.stream()
                .filter(group -> group.getUsers().contains(user))
                .map(group -> new GroupDto(group.getId(), group.getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}