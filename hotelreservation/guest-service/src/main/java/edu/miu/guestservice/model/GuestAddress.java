package edu.miu.guestservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GuestAddress {
    private Integer id;
    private String street;
    private String city;
    private Integer zipCode;
    private String state;
    private String country;

}
