package edu.miu.c590.hotelservice.repository;

import edu.miu.c590.hotelservice.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {

}
