package edu.miu.guestservice.repository;


import edu.miu.guestservice.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Integer> {
}
