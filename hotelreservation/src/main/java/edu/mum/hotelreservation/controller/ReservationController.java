package edu.mum.hotelreservation.controller;

import edu.mum.hotelreservation.entitiy.Reservation;
import edu.mum.hotelreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservation/getReservedRooms")
    public ResponseEntity<List<Reservation>> getReservedRooms(@RequestParam("fromDate")
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationFrom,
                                                              @RequestParam("toDate")
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationTo) {
        return new ResponseEntity<>(reservationService.getReservedRooms(reservationFrom, reservationTo),
                HttpStatus.OK);
    }


    @GetMapping("/reservation/getAvailableReservation")
    public ResponseEntity<Boolean> getAvailableReservation(@RequestParam("roomId") Integer roomId,
                                                           @RequestParam("fromDate")
                                                           @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationFrom,
                                                           @RequestParam("toDate")
                                                           @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationTo
    ) {
        return new ResponseEntity<>(reservationService.getAvailableReservation(roomId, reservationFrom, reservationTo),
                HttpStatus.OK);
    }

    @PostMapping("/reservation/bookReservation")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.saveReservation(reservation), HttpStatus.OK);
    }


}
