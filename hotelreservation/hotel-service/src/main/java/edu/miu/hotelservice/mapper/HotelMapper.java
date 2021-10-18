package edu.miu.hotelservice.mapper;

import edu.miu.hotelservice.entity.HotelEntity;
import edu.miu.hotelservice.model.Address;
import edu.miu.hotelservice.model.Hotel;
import edu.miu.hotelservice.model.Room;
import edu.miu.hotelservice.repository.HotelRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class HotelMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private HotelRepository hotelRepository;


    public List<Hotel> convertEntityToModel(List<HotelEntity> hotelEntities) {

        return CollectionUtils.emptyIfNull(hotelEntities)
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    public Hotel convertEntityToModel(HotelEntity hotelEntity) {

        Hotel hotel = new Hotel();
        hotel.setId(hotelEntity.getId());
        hotel.setName(hotelEntity.getName());
        hotel.setPhone(hotelEntity.getPhone());
        hotel.setEmail(hotelEntity.getEmail());

        Address address = addressMapper.convertEntityToModel(hotelEntity.getAddressEntity());
        if (Objects.nonNull(address)) {
            hotel.setAddress(address);
        }

        List<Room> rooms = roomMapper.convertEntityToModel(hotelEntity.getRoomEntities());
        if (CollectionUtils.isNotEmpty(rooms)) {
            hotel.setRooms(rooms);
        }

        return hotel;
    }


    public HotelEntity convertModelToEntity(Hotel hotel, HotelEntity hotelEntity) {

        if (Objects.nonNull(hotel)) {

            String hotelName = hotel.getName();
            if (Objects.nonNull(hotelName)) {
                hotelEntity.setName(hotelName);
            }

            String email = hotel.getEmail();
            if (Objects.nonNull(email)) {
                hotelEntity.setEmail(email);
            }

            Long phone = hotel.getPhone();
            if (Objects.nonNull(phone)) {
                hotelEntity.setPhone(phone);
            }

            HotelEntity savedHotelEntity = hotelRepository.save(hotelEntity);

            hotelEntity.setAddressEntity(
                    addressMapper.convertModelToEntity(hotel.getAddress(), savedHotelEntity));

            hotelEntity.setRoomEntities(roomMapper.convertModelToEntity(hotel.getRooms(), savedHotelEntity));
        }
        return hotelEntity;

    }
}
