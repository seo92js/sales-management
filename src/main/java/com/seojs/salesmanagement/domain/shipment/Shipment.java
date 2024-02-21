package com.seojs.salesmanagement.domain.shipment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class Shipment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trakingNum;
    private ShipmentStatus shipmentStatus;

    public void update(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public Shipment() {
        this.trakingNum = UUID.randomUUID().toString();
        this.shipmentStatus = ShipmentStatus.READY;
    }
}
