package edu.miu.hotelservice.service.helper;


import edu.miu.hotelservice.model.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "RESERVATION-SERVICE")
public interface ReservationFeignClient {

    @GetMapping(value = "${reservation.service.getReservedRooms.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Reservation> getReservedRooms(@RequestParam("fromDate") String fromDate,
                                       @RequestParam("toDate") String toDate);
}

