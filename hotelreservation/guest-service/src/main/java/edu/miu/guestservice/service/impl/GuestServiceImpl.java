package edu.miu.guestservice.service.impl;


import edu.miu.guestservice.entity.GuestEntity;
import edu.miu.guestservice.exception.BookingNotAvailableException;
import edu.miu.guestservice.exception.GuestNotFoundException;
import edu.miu.guestservice.mapper.GuestMapper;
import edu.miu.guestservice.service.GuestService;
import edu.miu.guestservice.service.helper.HotelFeignClient;
import edu.miu.guestservice.service.helper.ReservationFeignClient;
import edu.miu.guestservice.model.Guest;
import edu.miu.guestservice.model.Hotel;
import edu.miu.guestservice.model.Reservation;
import edu.miu.guestservice.model.Room;
import edu.miu.guestservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GuestMapper guestMapper;

    @Autowired
    private HotelFeignClient hotelFeignClient;

    @Autowired
    private ReservationFeignClient reservationFeignClient;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    private static final String CIRCUIT_BREAKER_ID = "circuitbreaker";


    @Override
    public Guest registerGuest(Guest guest) {
        GuestEntity savedGuestEntity = guestRepository.save(guestMapper.convertModelToEntity(guest, new GuestEntity()));
        return guestMapper.convertEntityToModel(savedGuestEntity);
    }


    @Override
    public Guest getGuest(Integer guestId) {
        Guest guest = null;
        Optional<GuestEntity> optionalGuestEntity = guestRepository.findById(guestId);
        if (optionalGuestEntity.isPresent()) {
            guest = guestMapper.convertEntityToModel(optionalGuestEntity.get());
        } else {
            throw new GuestNotFoundException ("Guest Id: " + guestId + " not found");
        }
        return guest;
    }

    @Override
    public Guest updateGuest(Guest guest, Integer guestId) {
        Guest updatedGuest = null;
        if (Objects.nonNull(guestId)) {
            Optional<GuestEntity> optionalGuestEntity = guestRepository.findById(guestId);
            if (optionalGuestEntity.isPresent()) {
                GuestEntity guestEntity = optionalGuestEntity.get();
                GuestEntity updatedGuestEntity = guestRepository.save(guestMapper.convertModelToEntity(guest, guestEntity));
                updatedGuest = guestMapper.convertEntityToModel(updatedGuestEntity);
            } else {
                throw new GuestNotFoundException("Guest Id: " + guestId + " not found");
            }
        }

        return updatedGuest;
    }

    @Override
    public boolean deleteGuest(Integer guestId) {
        boolean isDeleted = false;
        if (Objects.nonNull(guestId) && guestRepository.existsById(guestId)) {
            guestRepository.deleteById(guestId);
            isDeleted = true;
        }
        return isDeleted;
    }


    @Override
    public List<Hotel> viewHotels() {
        return hotelFeignClient.viewHotels();
    }

    @Override
    public List<Room> viewAvailableRooms(String startDate, String endDate) {
        return hotelFeignClient.getAvailableRooms(startDate, endDate);
    }

    @Override
    public Reservation bookReservation(Reservation reservation) {
        if (reservationFeignClient.getAvailableReservation(reservation.getRoomId(),
                reservation.getReservationFromDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate().toString(),
                reservation.getReservationToDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate().toString()).booleanValue()) {

            CircuitBreaker circuitBreaker = circuitBreakerFactory.create(CIRCUIT_BREAKER_ID);
            return circuitBreaker.run(() -> reservationFeignClient.bookReservation(reservation),
                    throwable -> fallbackBookReservation());

        } else {
            throw new BookingNotAvailableException ("Room id: " + reservation.getRoomId() +
                    " not available.");
        }
    }
    private Reservation fallbackBookReservation() {
        return new Reservation();
    }

}
