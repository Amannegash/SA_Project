package edu.miu.guestservice.service.helper;


import edu.miu.guestservice.model.Hotel;
import edu.miu.guestservice.model.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelFeignClient {

    @GetMapping(value = "${hotel.service.getHotels.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Hotel> viewHotels();

    @GetMapping(value = "${hotel.service.getAvailableRooms.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Room> getAvailableRooms(@RequestParam("fromDate") String fromDate,
                                 @RequestParam("toDate") String toDate);
}
