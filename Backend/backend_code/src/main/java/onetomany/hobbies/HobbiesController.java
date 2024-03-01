//package onetomany.hobbies;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.util.Optional;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class HobbiesController {
//
//    @Autowired
//    HobbiesRepository hobbiesRepository;
//
//    private String success = "{\"message\":\"success\"}";
//    private String failure = "{\"message\":\"failure\"}";
//
//    @GetMapping(path = "/hobbies")
//    List<Hobbies> getAllHobbies(){
//        return hobbiesRepository.findAll();
//    }
//
//    @GetMapping(path = "/hobbies/{id}")
//    Hobbies getHobbyById(@PathVariable int id){
//        return hobbiesRepository.findById(id);
//    }
//
//    @PostMapping(path = "/hobbies")
//    String createHobby(@RequestBody Hobbies hobby){
//        if (hobby == null)
//            return failure;
//        hobbiesRepository.save(hobby);
//        return success;
//    }
//
//    @PutMapping(path = "/hobbies/{id}")
//    Hobbies updateHobby(@PathVariable int id, @RequestBody Hobbies request){
//        Hobbies hobby = hobbiesRepository.findById(id);
//        if(hobby == null)
//            return null;
//        hobbiesRepository.save(request);
//        return hobbiesRepository.findById(id);
//    }
//
//    @DeleteMapping(path = "/hobbies/{id}")
//    String deleteHobby(@PathVariable int id){
//        hobbiesRepository.deleteById(id);
//        return success;
//    }
//}

package onetomany.hobbies;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;
//@RestController
//public class HobbiesController {
//
//    @Autowired
//    private HobbiesRepository hobbiesRepository;
//
//    private final String success = "{\"message\":\"success\"}";
//    private final String failure = "{\"message\":\"failure\"}";
//
//    @GetMapping(path = "/hobbies")
//    public List<Hobbies> getAllHobbies() {
//        return hobbiesRepository.findAll();
//    }
//
//    @GetMapping(path = "/hobbies/{id}")
//    public Hobbies getHobbyById(@PathVariable int id) {
//        // Assuming findById is changed to return Optional<Hobbies>
//        return hobbiesRepository.findById(id).orElse(null);
//    }
//
//    @PostMapping(path = "/hobbies")
//    public String createHobby(@RequestBody Hobbies hobby) {
//        if (hobby == null) {
//            return failure;
//        }
//        hobbiesRepository.save(hobby);
//        return success;
//    }
//
//    @PutMapping(path = "/hobbies/{id}")
//    public Hobbies updateHobby(@PathVariable int id, @RequestBody Hobbies request) {
//        return hobbiesRepository.findById(id).map(hobby -> {
//            hobby.setName(request.getName());
//            hobby.setHobbyType(request.getHobbyType()); // Updating hobby type
//            hobby.setUser(request.getUser());
//            hobbiesRepository.save(hobby);
//            return hobby;
//        }).orElse(null); // If the hobby doesn't exist, return null
//    }
//
//    @DeleteMapping(path = "/hobbies/{id}")
//    public String deleteHobby(@PathVariable int id) {
//        if (!hobbiesRepository.existsById(id)) {
//            return failure;
//        }
//        hobbiesRepository.deleteById(id);
//        return success;
//    }
//}
@RestController
public class HobbiesController {

    @Autowired
    private HobbiesRepository hobbiesRepository;

    private final String success = "{\"message\":\"success\"}";
    private final String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/hobbies")
    public ResponseEntity<List<Hobbies>> getAllHobbies() {
        List<Hobbies> hobbies = hobbiesRepository.findAll();
        return ResponseEntity.ok().body(hobbies);
    }

    @GetMapping(path = "/hobbies/{id}")
    public ResponseEntity<Hobbies> getHobbyById(@PathVariable int id) {
        Optional<Hobbies> hobby = hobbiesRepository.findById(id);
        return hobby.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/hobbies")
    public ResponseEntity<String> createHobby(@RequestBody Hobbies hobby) {
        if (hobby == null) {
            return ResponseEntity.badRequest().body(failure);
        }
        hobbiesRepository.save(hobby);
        return ResponseEntity.ok(success);
    }

    @PutMapping(path = "/hobbies/{id}")
    public ResponseEntity<?> updateHobby(@PathVariable int id, @RequestBody Hobbies hobbyDetails) {
        return hobbiesRepository.findById(id)
                .map(hobby -> {
                    hobby.setName(hobbyDetails.getName());
                    hobby.setHobbyType(hobbyDetails.getHobbyType());
                    hobbiesRepository.save(hobby);
                    return ResponseEntity.ok(hobby);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/hobbies/{id}")
    public ResponseEntity<String> deleteHobby(@PathVariable int id) {
        if (!hobbiesRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(failure);
        }
        hobbiesRepository.deleteById(id);
        return ResponseEntity.ok(success);
    }
}