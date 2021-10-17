package edu.miu.cs590.hotelservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hotel {

    private Integer id;
    private String name;
    private String email;
    private Long phone;
    private Address address;
    private List<Room> rooms;
}
