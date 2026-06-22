package com.MD.Service.offering.Service;

import com.MD.Service.offering.DTO.CategoryDTO;
import com.MD.Service.offering.DTO.SalonDTO;
import com.MD.Service.offering.DTO.ServiceDTO;
import com.MD.Service.offering.Model.ServiceOffering;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering creatingService(SalonDTO salonDTO,
                                    ServiceDTO serviceDTO,
                                    CategoryDTO categoryDTO);
    ServiceOffering updateService (Long serviceId,ServiceOffering service) throws Exception;
    Set<ServiceOffering> getAllServiceBySalonId(Long serviceId,Long salonId);

    Set<ServiceOffering> getServicesByIds(Set<Long> serviceIds);

    ServiceOffering getServicesById(Long serviceId) throws Exception;
}
