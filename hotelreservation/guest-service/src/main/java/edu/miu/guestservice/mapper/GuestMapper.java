package edu.miu.guestservice.mapper;


import edu.miu.guestservice.entity.GuestEntity;
import edu.miu.guestservice.model.Guest;
import edu.miu.guestservice.model.GuestAddress;
import edu.miu.guestservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GuestMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private GuestRepository guestRepository;

    public Guest convertEntityToModel(GuestEntity guestEntity) {
        Guest guest = new Guest();
        guest.setId(guestEntity.getId());
        guest.setName(guestEntity.getName());
        guest.setPhone(guestEntity.getPhone());
        guest.setEmail(guestEntity.getEmail());

        GuestAddress guestAddress = addressMapper.convertEntityToModel(guestEntity.getAddressEntity());
        if (Objects.nonNull(guestAddress)) {
            guest.setGuestAddress(guestAddress);
        }

        return guest;
    }

    public GuestEntity convertModelToEntity(Guest guest, GuestEntity guestEntity) {

        if (Objects.nonNull(guest)) {

            String guestName = guest.getName();
            if (Objects.nonNull(guestName)) {
                guestEntity.setName(guestName);
            }

            String email = guest.getEmail();
            if (Objects.nonNull(email)) {
                guestEntity.setEmail(email);
            }

            Long phone = guest.getPhone();
            if (Objects.nonNull(phone)) {
                guestEntity.setPhone(phone);
            }

            GuestEntity savedGuestEntity = guestRepository.save(guestEntity);

            guestEntity.setAddressEntity(
                    addressMapper.convertModelToEntity(guest.getGuestAddress(), savedGuestEntity));

        }
        return guestEntity;

    }
}

