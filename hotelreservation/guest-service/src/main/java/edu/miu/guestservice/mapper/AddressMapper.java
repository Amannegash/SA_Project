package edu.miu.guestservice.mapper;

import edu.miu.guestservice.entity.AddressEntity;
import edu.miu.guestservice.entity.GuestEntity;
import edu.miu.guestservice.model.GuestAddress;
import edu.miu.guestservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;


@Component
public class AddressMapper {

   @Autowired
    private AddressRepository  addressRepository;

    public GuestAddress convertEntityToModel(AddressEntity addressEntity) {

        GuestAddress guestAddress = null;

        if (Objects.nonNull(addressEntity)) {
            guestAddress = new GuestAddress();
            guestAddress.setId(addressEntity.getId());
            guestAddress.setStreet(addressEntity.getStreet());
            guestAddress.setCity(addressEntity.getCity());
            guestAddress.setZipCode(addressEntity.getZipcode());
            guestAddress.setState(addressEntity.getState());
            guestAddress.setCountry(addressEntity.getCountry());
        }

        return guestAddress;
    }

    public AddressEntity convertModelToEntity(GuestAddress guestAddress, GuestEntity guestEntity) {

        AddressEntity addressEntity = null;

        if (Objects.nonNull(guestAddress)) {

            addressEntity = getAddressEntity(guestAddress, guestEntity);

            String street = guestAddress.getStreet();
            if (Objects.nonNull(street)) {
                addressEntity.setStreet(street);
            }

            String city = guestAddress.getCity();
            if (Objects.nonNull(city)) {
                addressEntity.setCity(city);
            }

            Integer zipcode = guestAddress.getZipCode();
            if (Objects.nonNull(zipcode)) {
                addressEntity.setZipcode(zipcode);
            }

            String state = guestAddress.getState();
            if (Objects.nonNull(state)) {
                addressEntity.setState(state);
            }

            String country = guestAddress.getCountry();
            if (Objects.nonNull(country)) {
                addressEntity.setCountry(guestAddress.getCountry());

            }

        }
        return addressEntity;
    }

    private AddressEntity getAddressEntity(GuestAddress guestAddress, GuestEntity guestEntity) {
        AddressEntity addressEntity = new AddressEntity();

        if (Objects.nonNull(guestAddress.getId()) && guestAddress.getId() != 0) {
            Optional<AddressEntity> optionalAddressEntity = addressRepository.findById(guestAddress.getId());
            if (optionalAddressEntity.isPresent()) {
                addressEntity = optionalAddressEntity.get();
            }
        } else {
            addressEntity.setGuestEntity(guestEntity);
        }
        return addressEntity;
    }

}
