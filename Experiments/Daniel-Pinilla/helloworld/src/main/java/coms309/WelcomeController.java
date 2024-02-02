package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hello and welcome to COMS 309";
    }
    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hello and welcome toooo COMS 309: " + name;
    }

    @GetMapping("/check-email/{enteredEmail}")
    public String checkEmail(@PathVariable String enteredEmail) {

        String test= "mitra@iastate.edu";
        if (test.equals(enteredEmail)) {
            return "Email is the default one.";
        } else {
            return "Email is not the default one.";
        }
    }

    @GetMapping("/add/{num1}/{num2}")
    public String addNumbers(@PathVariable int num1, @PathVariable int num2) {
        int sum = num1 + num2;
        return "The sum of " + num1 + " and " + num2 + " is: " + sum;
    }



}
