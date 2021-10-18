package edu.miu.hotelservice.mapper;

import edu.miu.hotelservice.common.ActiveType;
import edu.miu.hotelservice.entity.HotelEntity;
import edu.miu.hotelservice.entity.RoomEntity;
import edu.miu.hotelservice.model.Room;
import edu.miu.hotelservice.repository.RoomRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoomMapper {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> convertEntityToModel(List<RoomEntity> roomEntities) {
        return CollectionUtils.emptyIfNull(roomEntities)
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }


    private Room convertEntityToModel(RoomEntity roomEntity) {
        Room room = new Room();
        room.setId(roomEntity.getId());
        room.setHotelId(roomEntity.getHotelEntity().getId());
        room.setRoomNo(roomEntity.getRoomNo());
        room.setRoomType(roomEntity.getRoomType());
        room.setPrice(roomEntity.getPrice());
        room.setRoomDescription(roomEntity.getRoomDescription());
        room.setActive( ActiveType.YES.getValue().equalsIgnoreCase(roomEntity.getIsActive()) ? Boolean.TRUE : Boolean.FALSE);

        return room;
    }


    public List<RoomEntity> convertModelToEntity(List<Room> rooms, HotelEntity hotelEntity) {
        return CollectionUtils.emptyIfNull(rooms)
                .stream()
                .filter(Objects::nonNull)
                .map(room -> convertModelToEntity(room, hotelEntity))
                .collect(Collectors.toList());
    }


    private RoomEntity convertModelToEntity(Room room, HotelEntity hotelEntity) {

        RoomEntity roomEntity = new RoomEntity();

        if (Objects.nonNull(room.getId()) && room.getId() != 0) {
            Optional<RoomEntity> optionalRoomEntity = roomRepository.findById(room.getId());
            if (optionalRoomEntity.isPresent()) {
                roomEntity = optionalRoomEntity.get();
            }
        } else {
            roomEntity = new RoomEntity();
            roomEntity.setHotelEntity(hotelEntity);
        }

        Integer roomNo = room.getRoomNo();
        if (Objects.nonNull(roomNo)) {
            roomEntity.setRoomNo(roomNo);
        }

        String roomType = room.getRoomType();
        if (Objects.nonNull(roomType)) {
            roomEntity.setRoomType(roomType);
        }

        String roomDescription = room.getRoomDescription();
        if (Objects.nonNull(roomDescription)) {
            roomEntity.setRoomDescription(roomDescription);
        }

        Double roomPrice = room.getPrice();
        if (Objects.nonNull(roomPrice)) {
            roomEntity.setPrice(roomPrice);
        }

        Boolean isActive = room.isActive();
        if (Objects.nonNull(isActive)) {
            roomEntity.setIsActive(isActive.booleanValue() ? ActiveType.YES.getValue() : ActiveType.NO.getValue());
        }

        return roomEntity;
    }
}
