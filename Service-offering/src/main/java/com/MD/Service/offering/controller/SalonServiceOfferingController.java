package com.MD.Service.offering.controller;


import com.MD.Service.offering.DTO.CategoryDTO;
import com.MD.Service.offering.DTO.SalonDTO;
import com.MD.Service.offering.DTO.ServiceDTO;
import com.MD.Service.offering.Model.ServiceOffering;
import com.MD.Service.offering.Service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/salon-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDTO serviceDTO){

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategoryId());

        ServiceOffering serviceOfferings = serviceOfferingService.creatingService(salonDTO,serviceDTO,categoryDTO);
        return ResponseEntity.ok(serviceOfferings);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(@RequestBody ServiceOffering serviceOffering, @PathVariable Long id) throws  Exception{

        ServiceOffering serviceOfferings = serviceOfferingService.updateService(id,serviceOffering);
        return ResponseEntity.ok(serviceOfferings);
    }
}
