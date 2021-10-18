package edu.miu.hotelservice.service.impl;



import edu.miu.hotelservice.entity.HotelEntity;
import edu.miu.hotelservice.mapper.HotelMapper;
import edu.miu.hotelservice.mapper.RoomMapper;
import edu.miu.hotelservice.model.Hotel;
import edu.miu.hotelservice.model.Reservation;
import edu.miu.hotelservice.model.Room;
import edu.miu.hotelservice.repository.HotelRepository;
import edu.miu.hotelservice.repository.RoomRepository;
import edu.miu.hotelservice.service.HotelService;
import edu.miu.hotelservice.service.helper.ReservationFeignClient;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private ReservationFeignClient reservationFeignClient;

    @Override
    public List<Hotel> getHotels() {
        return hotelMapper.convertEntityToModel(hotelRepository.findAll());
    }

    @Override
    public Hotel getHotel(Integer hotelId) {
        Hotel hotel = null;
        Optional<HotelEntity> optionalHotelEntity = hotelRepository.findById(hotelId);
        if (optionalHotelEntity.isPresent()) {
            hotel = hotelMapper.convertEntityToModel(optionalHotelEntity.get());
        }
        return hotel;
    }


    @Override
    public Hotel createHotel(Hotel hotel) {
        HotelEntity savedHotelEntity = hotelRepository.save(hotelMapper.convertModelToEntity(hotel, new HotelEntity()));
        return hotelMapper.convertEntityToModel(savedHotelEntity);
    }

    @Override
    public Hotel updateHotel(Hotel hotel, Integer hotelId) {
        Hotel updatedHotel = null;
        if (Objects.nonNull(hotelId)) {
            Optional<HotelEntity> optionalHotelEntity = hotelRepository.findById(hotelId);
            if (optionalHotelEntity.isPresent()) {
                HotelEntity hotelEntity = optionalHotelEntity.get();
                HotelEntity updatedHotelEntity = hotelRepository.save(hotelMapper.convertModelToEntity(hotel, hotelEntity));
                updatedHotel = hotelMapper.convertEntityToModel(updatedHotelEntity);
            }
        }

        return updatedHotel;
    }

    @Override
    public boolean deleteHotel(Integer hotelId) {
        boolean isDeleted = false;
        if (Objects.nonNull(hotelId) && hotelRepository.existsById(hotelId)) {
            hotelRepository.deleteById(hotelId);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public List<Room> getAvailableRooms(String fromDate, String toDate) {

        List<Reservation> reservations = reservationFeignClient.getReservedRooms(fromDate, toDate);

        List<Integer> reservedRoomIds = CollectionUtils.emptyIfNull(reservations)
                .stream()
                .filter(Objects::nonNull)
                .map(Reservation::getRoomId)
                .collect(Collectors.toList());

        return roomMapper.convertEntityToModel(
                roomRepository.findByIdNotIn(reservedRoomIds));
    }
}
