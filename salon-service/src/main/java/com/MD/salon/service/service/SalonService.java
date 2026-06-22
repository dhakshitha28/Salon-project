package com.MD.salon.service.service;

import com.MD.salon.service.model.Salon;
import com.MD.salon.service.payLoad.DTO.SalonDTO;
import com.MD.salon.service.payLoad.DTO.UserDTO;

import java.util.List;

public interface SalonService {

    Salon creatSalon(SalonDTO salon, UserDTO user);

    Salon updateSalon(SalonDTO salon, UserDTO user,Long salonId) throws Exception;

    List<Salon> getAllSalons();

    Salon getSalonById(Long salonId) throws Exception;

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> searchSalonByCityName(String cityName);

}
