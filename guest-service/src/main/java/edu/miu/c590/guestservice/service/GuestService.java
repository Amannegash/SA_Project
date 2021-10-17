package edu.miu.c590.guestservice.service;

import edu.miu.c590.guestservice.model.Guest;
import edu.miu.c590.guestservice.model.Hotel;
import edu.miu.c590.guestservice.model.Reservation;
import edu.miu.c590.guestservice.model.Room;

import java.util.List;

public interface GuestService {
    public Guest registerGuest(Guest guest);

    public Guest getGuest(Integer guestId);

    public Guest updateGuest(Guest guest, Integer guestId);

    public boolean deleteGuest(Integer guestId);

    public List<Hotel> viewHotels();

    public  List<Room> viewAvailableRooms(String startDate, String EndDate);

    public Reservation bookReservation(Reservation reservation);
}
