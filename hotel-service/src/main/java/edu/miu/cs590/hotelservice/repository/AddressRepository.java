package edu.miu.cs590.hotelservice.repository;

import edu.miu.cs590.hotelservice.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
