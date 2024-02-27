package onetomany.hobbies;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class HobbiesController {

    @Autowired
    HobbiesRepository hobbiesRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/hobbies")
    List<Hobbies> getAllHobbies(){
        return hobbiesRepository.findAll();
    }

    @GetMapping(path = "/hobbies/{id}")
    Hobbies getHobbyById(@PathVariable int id){
        return hobbiesRepository.findById(id);
    }

    @PostMapping(path = "/hobbies")
    String createHobby(@RequestBody Hobbies hobby){
        if (hobby == null)
            return failure;
        hobbiesRepository.save(hobby);
        return success;
    }

    @PutMapping(path = "/hobbies/{id}")
    Hobbies updateHobby(@PathVariable int id, @RequestBody Hobbies request){
        Hobbies hobby = hobbiesRepository.findById(id);
        if(hobby == null)
            return null;
        hobbiesRepository.save(request);
        return hobbiesRepository.findById(id);
    }

    @DeleteMapping(path = "/hobbies/{id}")
    String deleteHobby(@PathVariable int id){
        hobbiesRepository.deleteById(id);
        return success;
    }
}
