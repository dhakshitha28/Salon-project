package com.MD.Service.offering.Service.Impl;

import com.MD.Service.offering.DTO.CategoryDTO;
import com.MD.Service.offering.DTO.SalonDTO;
import com.MD.Service.offering.DTO.ServiceDTO;
import com.MD.Service.offering.Model.ServiceOffering;
import com.MD.Service.offering.Service.ServiceOfferingService;
import com.MD.Service.offering.repository.ServiceOfferingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ServiceOfferingImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;
    @Override
    public ServiceOffering creatingService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO) {

        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setSalonId(salonDTO.getId());
        serviceOffering.setName(serviceDTO.getName());
        serviceOffering.setImageUrl(serviceDTO.getImageUrl());
        serviceOffering.setCategoryId(categoryDTO.getId());
        serviceOffering.setDescription(serviceDTO.getDescription());
        serviceOffering.setPrice(serviceDTO.getPrice());
        serviceOffering.setDuration(serviceDTO.getDuration());
        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {

        ServiceOffering existingService = serviceOfferingRepository.findById(serviceId).orElse(null);

        if (existingService == null) {
            throw new Exception("Service with ID " + serviceId + " not found.");
        }


        existingService.setName(service.getName());
        existingService.setImageUrl(service.getImageUrl());
        existingService.setDescription(service.getDescription());
        existingService.setPrice(service.getPrice());
        existingService.setDuration(service.getDuration());
        return serviceOfferingRepository.save(existingService);


    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {

        Set<ServiceOffering> serviceOfferings = serviceOfferingRepository.findBySalonId(salonId);
        if(categoryId!=null){
            serviceOfferings=serviceOfferings.stream().filter(
                    serviceOffering -> serviceOffering.getCategoryId()!= null &&
                            serviceOffering.getCategoryId()==categoryId).collect(Collectors.toSet());
        }

        return serviceOfferings;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> serviceIds) {
         List<ServiceOffering> services=serviceOfferingRepository.findAllById(serviceIds);
         return new HashSet<>(services);
    }

    @Override
    public ServiceOffering getServicesById(Long serviceId) throws Exception {
        ServiceOffering existingService = serviceOfferingRepository.findById(serviceId).orElse(null);

        if (existingService == null) {
            throw new Exception("Service with ID " + serviceId + " not found.");
        }

        return existingService;

    }
}
