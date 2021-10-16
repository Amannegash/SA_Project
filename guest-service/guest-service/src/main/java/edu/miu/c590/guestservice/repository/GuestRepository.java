package edu.miu.c590.guestservice.repository;

import edu.miu.c590.guestservice.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  The repository for GuestEntity
 */
@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Integer> {
}
