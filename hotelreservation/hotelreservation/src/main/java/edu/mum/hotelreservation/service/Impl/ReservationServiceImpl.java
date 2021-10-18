package edu.mum.hotelreservation.service.Impl;


import edu.mum.hotelreservation.common.BookingStatus;
import edu.mum.hotelreservation.common.Reservation;
import edu.mum.hotelreservation.entitiy.ReservationEntity;
import edu.mum.hotelreservation.common.ReservationMapper;
import edu.mum.hotelreservation.repository.ReservationRepository;
import edu.mum.hotelreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public List<Reservation> getReservedRooms(Date fromDate, Date toDate) {
        List<ReservationEntity> reservationEntities = reservationRepository.
                findByReservationFromDateAndReservationToDateAndBookingStatus(fromDate,
                        toDate, BookingStatus.BOOKED.getStatus());
        return reservationMapper.convertEntityToModel(reservationEntities);
    }


    @Override
    public Reservation saveReservation(Reservation reservation) {
        ReservationEntity savedReservationEntity = reservationRepository.save(
                reservationMapper.convertModelToEntity(reservation));
        return reservationMapper.convertEntityToModel(savedReservationEntity);
    }


    @Override
    public Boolean getAvailableReservation(Integer roomId, Date fromDate, Date toDate) {
        Optional<ReservationEntity> optionalReservationEntity =
                reservationRepository.findByRoomIdAndReservationFromDateAndReservationToDate(
                        roomId, fromDate, toDate);

       return optionalReservationEntity.isEmpty() ? true : false;
    }


}
