package com.seojs.salesmanagement.domain.shipment.dto;

import com.seojs.salesmanagement.domain.shipment.ShipmentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShipmentUpdateDto {
    private ShipmentStatus shipmentStatus;

    @Builder
    public ShipmentUpdateDto(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}
