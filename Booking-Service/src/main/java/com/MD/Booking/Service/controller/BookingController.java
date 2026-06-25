package com.MD.Booking.Service.controller;


import com.MD.Booking.Service.DTO.BookingRequest;
import com.MD.Booking.Service.DTO.SalonDTO;
import com.MD.Booking.Service.DTO.ServiceDTO;
import com.MD.Booking.Service.DTO.UserDTO;
import com.MD.Booking.Service.Repository.BookingRepository;
import com.MD.Booking.Service.Service.BookingService;
import com.MD.Booking.Service.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBookings(
            @RequestParam Long salonId,
            @RequestBody BookingRequest bookingRequest
            ) throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        SalonDTO  salonDTO = new SalonDTO();
        salonDTO.setId(salonId);

        Set<ServiceDTO> serviceDTOSet = new HashSet<>();

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(999);
        serviceDTO.setDuration(28);
        serviceDTO.setName("Hair Cut");
        serviceDTOSet.add(serviceDTO);

        Booking booking =bookingService.creatrBooking(bookingRequest,userDTO,salonDTO,serviceDTOSet);

        return ResponseEntity.ok(booking);
    }

    public ResponseEntity<Set<BookingDTO>> getBookingsByCustomer(
          ){
        List<Booking> booking =bookingService.getBookingByCustomer(1L);

    }
}
