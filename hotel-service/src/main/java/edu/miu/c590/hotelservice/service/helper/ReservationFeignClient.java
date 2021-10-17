package edu.miu.c590.hotelservice.service.helper;

import edu.miu.c590.hotelservice.model.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${reservation.service.name}")
public interface ReservationFeignClient {

    @GetMapping(value = "${reservation.service.getReservedRooms.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Reservation> getReservedRooms(@RequestParam("fromDate") String fromDate,
                                       @RequestParam("toDate") String toDate);
}

