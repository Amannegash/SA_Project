package edu.mum.hotelreservation.repository;

import edu.mum.hotelreservation.entitiy.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

    List<ReservationEntity> findByReservationFromDateAndReservationToDateAndBookingStatus(Date fromDate,
                                                                                          Date toDate,
                                                                                          String bookingStatus);

    Optional<ReservationEntity> findByRoomIdAndReservationFromDateAndReservationToDate(Integer RoomId,
                                                                                 Date fromDate,
                                                                                 Date toDate);
}
