package aaade.qaservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {
    private Long id;
    private String content;
    private Date dateSent;
}
