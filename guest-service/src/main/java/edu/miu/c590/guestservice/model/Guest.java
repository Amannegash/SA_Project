package edu.miu.c590.guestservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Guest {

    private Integer id;
    private String name;
    private String email;
    private Long phone;
    private GuestAddress guestAddress;

}
