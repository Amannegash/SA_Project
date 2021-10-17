package edu.miu.cs590.hotelservice.controller;

import edu.miu.cs590.hotelservice.model.Hotel;
import edu.miu.cs590.hotelservice.model.Room;
import edu.miu.cs590.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelControllerImpl implements HotelController {

    @Autowired
    private HotelService hotelService;

    @Override
    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getHotels() {
        return new ResponseEntity<>(hotelService.getHotels(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") Integer hotelId) {
        return new ResponseEntity<>(hotelService.getHotel(hotelId), HttpStatus.OK);
    }

    @Override
    @PostMapping("/hotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.createHotel(hotel),HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/hotel{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable Integer hotelId) {
        return new ResponseEntity<>(hotelService.updateHotel(hotel,hotelId),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/hotel/{hotelId}")
    public ResponseEntity<Integer> deleteHotel(@PathVariable Integer hotelId) {

        ResponseEntity<Integer> responseEntity =null;
        if(hotelService.deleteHotel(hotelId)) {
            responseEntity = new ResponseEntity<>(hotelId, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(hotelId,HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    @GetMapping("/hotel/availableRooms")
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestParam("fromDate")  String fromDate, @RequestParam("toDate") String toDate) {
        return new ResponseEntity<List<Room>>(hotelService.getAvailableRooms(fromDate,toDate), HttpStatus.OK);
    }
}
