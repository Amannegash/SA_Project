package edu.mum.hotelreservation.common;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;


@Data
@Component
public class Reservation implements Serializable {

    private Integer id;
    private Integer guestId;
    private Integer hotelId;
    private Integer roomId;
    private Date reservationFromDate;
    private Date reservationToDate;
    private String reservationStatus;
}
