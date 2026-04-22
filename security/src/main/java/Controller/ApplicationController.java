package Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth")
public class ApplicationController {

    @GetMapping("/process")
    public String process() {
        return "passed the string security through DB";
    }
}
// another controller to test the authentication of the user. 
// This is a simple controller that returns a string when the user is authenticated.
