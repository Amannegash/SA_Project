package edu.miu.guestservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Reservation {

    private Integer id;
    private Integer guestId;
    private Integer hotelId;
    private Integer roomId;
    private Date reservationFromDate;
    private Date reservationToDate;
    private String reservationStatus;
}
