package edu.miu.guestservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zipcode")
    private Integer zipcode;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", referencedColumnName = "id", nullable = false)
    private GuestEntity guestEntity;
}

