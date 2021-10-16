package edu.miu.c590.guestservice.service.helper;

import edu.miu.c590.guestservice.model.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${reservation.service.name}")
public interface ReservationFeignClient {

    @GetMapping(value = "${reservation.service.getAvailableReservation.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean getAvailableReservation(@RequestParam Integer roomId,
                                    @RequestParam("fromDate") String fromDate,
                                    @RequestParam("toDate") String toDate);

    @PostMapping(value = "${reservation.service.bookReservation.path}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Reservation bookReservation(@RequestBody Reservation reservation);
}
