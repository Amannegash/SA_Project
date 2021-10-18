package edu.miu.hotelservice.mapper;


import edu.miu.hotelservice.entity.AddressEntity;
import edu.miu.hotelservice.entity.HotelEntity;
import edu.miu.hotelservice.model.Address;
import edu.miu.hotelservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;


@Component
public class AddressMapper {

    @Autowired
    private AddressRepository addressRepository;


    public Address convertEntityToModel(AddressEntity addressEntity) {

        Address address = null;

        if (Objects.nonNull(addressEntity)) {
            address = new Address();
            address.setId(addressEntity.getId());
            address.setStreet(addressEntity.getStreet());
            address.setCity(addressEntity.getCity());
            address.setZipCode(addressEntity.getZipcode());
            address.setState(addressEntity.getState());
            address.setCountry(addressEntity.getCountry());
        }

        return address;
    }


    public AddressEntity convertModelToEntity(Address address, HotelEntity hotelEntity) {

        AddressEntity addressEntity = null;

        if (Objects.nonNull(address)) {

            addressEntity = getAddressEntity(address, hotelEntity);

            String street = address.getStreet();
            if (Objects.nonNull(street)) {
                addressEntity.setStreet(street);
            }

            String city = address.getCity();
            if (Objects.nonNull(city)) {
                addressEntity.setCity(city);
            }

            Integer zipcode = address.getZipCode();
            if (Objects.nonNull(zipcode)) {
                addressEntity.setZipcode(zipcode);
            }

            String state = address.getState();
            if (Objects.nonNull(state)) {
                addressEntity.setState(state);
            }

            String country = address.getCountry();
            if (Objects.nonNull(country)) {
                addressEntity.setCountry(address.getCountry());

            }

        }
        return addressEntity;
    }


    private AddressEntity getAddressEntity(Address address, HotelEntity hotelEntity) {
        AddressEntity addressEntity = new AddressEntity();

        if (Objects.nonNull(address.getId()) && address.getId() != 0) {
            Optional<AddressEntity> optionalAddressEntity = addressRepository.findById(address.getId());
            if (optionalAddressEntity.isPresent()) {
                addressEntity = optionalAddressEntity.get();
            }
        } else {
            addressEntity = new AddressEntity();
            addressEntity.setHotelEntity(hotelEntity);
        }
        return addressEntity;
    }

}
