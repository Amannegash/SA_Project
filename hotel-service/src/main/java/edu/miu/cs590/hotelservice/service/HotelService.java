package edu.miu.cs590.hotelservice.service;

import edu.miu.cs590.hotelservice.model.Hotel;
import edu.miu.cs590.hotelservice.model.Room;

import java.util.List;

public interface HotelService {

    public List<Hotel> getHotels();


    public Hotel getHotel(Integer hotelId);


    public Hotel createHotel(Hotel hotel);


    public Hotel updateHotel(Hotel hotel, Integer hotelId);


    public boolean deleteHotel(Integer hotelId);


    public List<Room> getAvailableRooms(String fromDate, String toDate);
}
