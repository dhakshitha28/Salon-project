package com.MD.salon.service.mapper;

import com.MD.salon.service.model.Salon;
import com.MD.salon.service.payLoad.DTO.SalonDTO;

public class SalonMapper {
    public static SalonDTO mapToDTO (Salon salon) {
        SalonDTO salonDTO = new SalonDTO();

        salonDTO.setId(salon.getId());
        salonDTO.setName(salon.getName());
        salonDTO.setImages(salon.getImages());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setPhoneNumber(salon.getPhoneNumber());
        salonDTO.setEmail(salon.getEmail());
        salonDTO.setCity(salon.getCity());
        salonDTO.setOwnerId(salon.getOwnerId());
        salonDTO.setOpeningTime(salon.getOpeningTime());
        salonDTO.setClosingTime(salon.getClosingTime());

        return salonDTO;
    }
}

