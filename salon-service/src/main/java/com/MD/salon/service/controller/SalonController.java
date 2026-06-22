package com.MD.salon.service.controller;

import com.MD.salon.service.mapper.SalonMapper;
import com.MD.salon.service.model.Salon;
import com.MD.salon.service.payLoad.DTO.SalonDTO;
import com.MD.salon.service.payLoad.DTO.UserDTO;
import com.MD.salon.service.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {
    private final SalonService salonService;

    // http://localhost:5002/api/salons

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon= salonService.creatSalon(salonDTO,userDTO);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    // http://localhost:5002/api/salons/id
    @PatchMapping ("/{salonId}")
    public ResponseEntity<SalonDTO> updateSalon(@PathVariable Long salonId,@RequestBody SalonDTO salonDTO) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon= salonService.updateSalon(salonDTO,userDTO,salonId);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    //http://localhost:5002/api/salons
    @GetMapping()
    public ResponseEntity<List<SalonDTO>> getSalon() throws Exception {

        List<Salon> salons= salonService.getAllSalons();

        List<SalonDTO> salonDTOS=salons.stream().map((salon)->
                {
                    SalonDTO salonDTO=SalonMapper.mapToDTO(salon);
                    return salonDTO;
                }
                ).toList();

        return ResponseEntity.ok(salonDTOS);
    }
    //http://localhost:5002/api/salons/ID
    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonById(
            @PathVariable Long salonId
    ) throws Exception {

        Salon salon=salonService.getSalonById(salonId);

        SalonDTO salonDTO = SalonMapper.mapToDTO(salon);

        return ResponseEntity.ok(salonDTO);
    }

    //https://localhost:5002/api/salons/search?city=mumbai
    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalon(
            @RequestParam("city") String city
    ) throws Exception {

        List<Salon> salons= salonService.searchSalonByCityName(city);

        List<SalonDTO> salonDTOS=salons.stream().map((salon)->
                {
                    SalonDTO salonDTO=SalonMapper.mapToDTO(salon);
                    return salonDTO;
                }
        ).toList();

        return ResponseEntity.ok(salonDTOS);
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.getSalonByOwnerId(userDTO.getId());
        SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);
    }

}

