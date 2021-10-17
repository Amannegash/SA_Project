package edu.miu.cs590.hotelservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    private Integer id;
    private String street;
    private String city;
    private Integer zipCode;
    private String state;
    private String country;

    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode=" + zipCode +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
