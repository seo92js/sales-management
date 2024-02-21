package com.seojs.salesmanagement.service;

import com.seojs.salesmanagement.domain.shipment.Shipment;
import com.seojs.salesmanagement.domain.shipment.ShipmentRepository;
import com.seojs.salesmanagement.domain.shipment.dto.ShipmentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    @Transactional
    public Long save(Shipment shipment) {
        return shipmentRepository.save(shipment).getId();
    }

    @Transactional
    public Shipment findById(Long id) {
        return shipmentRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void update(Long id, ShipmentUpdateDto shipmentUpdateDto) {
        Shipment shipment = shipmentRepository.findById(id).orElseThrow();

        shipment.update(shipmentUpdateDto.getShipmentStatus());
    }
}
