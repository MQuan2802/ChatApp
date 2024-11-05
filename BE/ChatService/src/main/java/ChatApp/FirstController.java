package ChatApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class FirstController {

    @Autowired
    MailService MailService;



    @RequestMapping(value = "ping")
    public String ping() {
        return ("Service is alive");
    }

    @RequestMapping(value = "/testSendingMail", method = RequestMethod.GET)
    public ResponseEntity testSendingMail() {
        this.MailService.testSendMessage();
        return ResponseEntity.ok("Success");
    }
}
