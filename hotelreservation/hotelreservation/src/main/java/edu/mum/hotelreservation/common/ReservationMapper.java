package edu.mum.hotelreservation.common;


import edu.mum.hotelreservation.common.BookingStatus;
import edu.mum.hotelreservation.common.Reservation;
import edu.mum.hotelreservation.entitiy.ReservationEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;



import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class ReservationMapper {

   
    public List<Reservation> convertEntityToModel(List<ReservationEntity> reservationEntities) {

        return CollectionUtils.emptyIfNull(reservationEntities)
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());

    }

    
    public Reservation convertEntityToModel(ReservationEntity reservationEntity) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationEntity.getId());
        reservation.setGuestId(reservationEntity.getGuestId());
        reservation.setHotelId(reservationEntity.getHotelId());
        reservation.setRoomId(reservationEntity.getRoomId());
        reservation.setReservationToDate(reservationEntity.getReservationToDate());
        reservation.setReservationFromDate(reservationEntity.getReservationFromDate());
        reservation.setReservationStatus(reservationEntity.getBookingStatus());
        return reservation;
    }

    public ReservationEntity convertModelToEntity(Reservation reservation) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setHotelId(reservation.getHotelId());
        reservationEntity.setGuestId(reservation.getGuestId());
        reservationEntity.setRoomId(reservation.getRoomId());
        reservationEntity.setReservationFromDate(reservation.getReservationFromDate());
        reservationEntity.setReservationToDate(reservation.getReservationToDate());
        reservationEntity.setBookingStatus( BookingStatus.BOOKED.getStatus());
        return reservationEntity;
    }
}
