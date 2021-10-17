package edu.miu.c590.hotelservice.repository;

import edu.miu.c590.hotelservice.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    List<RoomEntity> findByIdNotIn(List<Integer> reservedRoomIds);
}

