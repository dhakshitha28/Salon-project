package com.MD.Service.offering.controller;


import com.MD.Service.offering.Model.ServiceOffering;
import com.MD.Service.offering.Service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/service-offering")
@RequiredArgsConstructor
public class ServicesOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>>getServicesBySalonId(@PathVariable Long salonId, @RequestParam(required = false)Long categoryId){

        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getAllServiceBySalonId(salonId,categoryId);
        return ResponseEntity.ok(serviceOfferings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering>getServicesById(@PathVariable Long id) throws Exception {

        ServiceOffering serviceOfferings = serviceOfferingService.getServicesById(id);
        return ResponseEntity.ok(serviceOfferings);
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>>getServicesByIds(@PathVariable Set<Long> ids){

        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getServicesByIds(ids);
        return ResponseEntity.ok(serviceOfferings);
    }
}
