package aaade.controller;

import aaade.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * EmailSendController class provides a RESTful controller for sending emails.
 */
@RestController
@RequestMapping("/api/mail")
public class EmailSendController {

    private final EmailService emailService;

    public EmailSendController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendMail(@RequestParam(value = "file", required = false) MultipartFile[] file, String to,
                           String[] cc, String subject, String body) {
        return emailService.sendMail(file, to, cc, subject, body);
    }
}
