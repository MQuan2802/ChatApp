package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.demo.MailService;

import java.util.List;


@RestController
@RequestMapping("api")
public class FirstController {

//    @Value("${application.test}")
//    String testString;
    @Autowired
    MailService MailService;

    @Autowired
    private MongoDemoRepository userRepository;

    @RequestMapping(value = "ping")
    public String ping() {
        return ("Service is alive");
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/testSendingMail", method = RequestMethod.GET)
    public ResponseEntity testSendingMail() {
        this.MailService.testSendMessage();
        return ResponseEntity.ok("Success");
    }
}
