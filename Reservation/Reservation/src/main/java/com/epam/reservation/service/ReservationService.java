package com.epam.reservation.service;

import com.epam.reservation.model.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {


    public List<Reservation> getReservedRooms(Date fromDate, Date toDate);


    public Reservation saveReservation(Reservation reservation);


    public Boolean getAvailableReservation(Integer roomId, Date fromDate, Date toDate);
}
