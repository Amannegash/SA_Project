package edu.miu.hotelservice.controller;

import edu.miu.hotelservice.model.Hotel;
import edu.miu.hotelservice.model.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface HotelController {


    public ResponseEntity<List<Hotel>> getHotels();


    public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") Integer hotelId);


    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel);

    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable Integer hotelId);


    public ResponseEntity<Integer> deleteHotel(@PathVariable Integer hotelId);

    public ResponseEntity<List<Room>> getAvailableRooms(@RequestParam("fromDate") String fromDate,
                                                        @RequestParam("toDate") String toDate);

}
