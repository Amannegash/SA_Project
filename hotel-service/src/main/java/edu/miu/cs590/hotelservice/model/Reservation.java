package edu.miu.cs590.hotelservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private Integer id;
    private Integer guestId;
    private Integer hotelId;
    private Integer roomId;
    private Date reservationFromDate;
    private Date reservationToDate;
    private String reservationStatus;
}
