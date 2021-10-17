package edu.miu.cs590.hotelservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "room_no")
    private Integer roomNo;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_active")
    private String isActive;

    @Column(name = "room_description")
    private String roomDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id",referencedColumnName = "id", nullable = false)
    private HotelEntity hotelEntity;

}
