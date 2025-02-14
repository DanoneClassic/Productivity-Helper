package aaade.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);
            for (MultipartFile multipartFile : file) {
                if (multipartFile.getOriginalFilename() != null) {
                    mimeMessageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            javaMailSender.send(mimeMessage);
            return "Email sent";
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @KafkaListener(topics = "registration-topic")
    public void onRegistration(String email) {
        sendMail(new MultipartFile[]{}, email, new String[]{}, "Welcome to Our Service", "Thank you for registering with us!");
    }
}
