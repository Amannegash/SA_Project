package edu.miu.c590.guestservice.controller;


import edu.miu.c590.guestservice.model.Guest;
import edu.miu.c590.guestservice.model.Hotel;
import edu.miu.c590.guestservice.model.Reservation;
import edu.miu.c590.guestservice.model.Room;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GuestController {


    public ResponseEntity<Guest> getGuest(Integer guestId);

    public ResponseEntity<Guest> registerGuest(Guest guest);

    public ResponseEntity<Guest> updateGuest(Guest guest, Integer guestId);

    public ResponseEntity<Integer> deleteGuest(Integer guestId);

    public ResponseEntity<List<Hotel>> viewHotels();

    public ResponseEntity<List<Room>> viewAvailableRooms(String startDate, String endDate);

    public ResponseEntity<Reservation> bookReservation(Reservation reservation);
}
