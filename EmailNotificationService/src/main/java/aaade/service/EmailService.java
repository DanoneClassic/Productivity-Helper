package aaade.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * EmailService interface provides a method for sending emails.
 */
public interface EmailService {
    /**
     * Sends an email with the given parameters.
     *
     * @param file    the file to attach to the email
     * @param to      the recipient of the email
     * @param cc      the recipients to carbon copy
     * @param subject the subject of the email
     * @param body    the body of the email
     * @return a message indicating the result of sending the email
     */
    String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body);
}
