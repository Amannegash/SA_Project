package edu.miu.c590.guestservice.repository;


import edu.miu.c590.guestservice.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  The repository for AddressEntity
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
