package edu.miu.guestservice.controller;

import edu.miu.guestservice.service.GuestService;
import edu.miu.guestservice.model.Guest;
import edu.miu.guestservice.model.Hotel;
import edu.miu.guestservice.model.Reservation;
import edu.miu.guestservice.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GuestControllerImpl implements GuestController {

    @Autowired
    private GuestService guestService;


    @Override
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<Guest> getGuest(@PathVariable("guestId") Integer guestId) {
        return new ResponseEntity<>(guestService.getGuest(guestId), HttpStatus.OK);
    }


    @Override
    @PostMapping("/guest")
    public ResponseEntity<Guest> registerGuest(@RequestBody Guest guest) {
        return new ResponseEntity<>(guestService.registerGuest(guest), HttpStatus.CREATED);
    }


    @Override
    @PutMapping("/guest/{guestId}")
    public ResponseEntity<Guest> updateGuest(@RequestBody Guest guest, @PathVariable Integer guestId) {
        return new ResponseEntity<>(guestService.updateGuest(guest, guestId), HttpStatus.OK);
    }


    @Override
    @DeleteMapping("/guest/{guestId}")
    public ResponseEntity<Integer> deleteGuest(@PathVariable Integer guestId) {

        ResponseEntity<Integer> responseEntity = null;
        if (guestService.deleteGuest(guestId)) {
            responseEntity = new ResponseEntity<>(guestId, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(guestId, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }


    @Override
    @GetMapping("/guest/viewHotels")
    public ResponseEntity<List<Hotel>> viewHotels() {
        return new ResponseEntity<>(guestService.viewHotels(), HttpStatus.OK);
    }


    @Override
    @GetMapping("/guest/availableRooms")
    public ResponseEntity<List<Room>> viewAvailableRooms(@RequestParam("fromDate") String startDate,
                                                         @RequestParam("toDate") String endDate) {
        return new ResponseEntity<>(guestService.viewAvailableRooms(startDate, endDate),
                HttpStatus.OK);
    }


    @Override
    @PostMapping("/guest/bookReservation")
    public ResponseEntity<Reservation> bookReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(guestService.bookReservation(reservation), HttpStatus.OK);
    }

}
