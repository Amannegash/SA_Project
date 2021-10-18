package edu.miu.guestservice.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "guests")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "guestEntity")
    private AddressEntity addressEntity;
}

