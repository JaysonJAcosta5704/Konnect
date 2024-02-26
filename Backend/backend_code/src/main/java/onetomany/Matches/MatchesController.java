package onetomany.Matches;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MatchesController {
    @Autowired
    MatchesRepository matchesRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";


    @GetMapping(path = "/matches")
    List<Match> getAllMatches(){
        return  matchesRepository.findAll();
    }

    @GetMapping(path = "/matches/{id}")
    Match getLaptopById(@PathVariable int id){
        return matchesRepository.findById(id);
    }

    @PostMapping(path = "/matches")
    String createLaptop(@RequestBody Match match){
        if (match == null)
            return failure;
        matchesRepository.save(match);
        return success;
    }

    @PutMapping(path = "/matches/{id}")
    Match updateLaptop(@PathVariable int id, @RequestBody Match request){
        Match match = matchesRepository.findById(id);
        if(match == null)
            return null;
        matchesRepository.save(request);
        return matchesRepository.findById(id);
    }

    @DeleteMapping(path = "/matches/{id}")
    String deleteLaptop(@PathVariable long id){
        matchesRepository.deleteById(id);
        return success;
    }
}
