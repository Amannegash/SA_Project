package edu.miu.guestservice.controller;

import edu.miu.guestservice.model.Guest;
import edu.miu.guestservice.model.Hotel;
import edu.miu.guestservice.model.Reservation;
import edu.miu.guestservice.model.Room;
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
