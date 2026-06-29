package com.MD.Booking.Service.controller;


import com.MD.Booking.Service.DTO.*;
import com.MD.Booking.Service.Mapper.BookingMapper;
import com.MD.Booking.Service.Repository.BookingRepository;
import com.MD.Booking.Service.Service.BookingService;
import com.MD.Booking.Service.domain.BookingStatus;
import com.MD.Booking.Service.model.Booking;
import com.MD.Booking.Service.model.SalonReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/bookings"})
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

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salonId);

        Set<ServiceDTO> serviceDTOSet = new HashSet<>();

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(999);
        serviceDTO.setDuration(28);
        serviceDTO.setName("Hair Cut");
        serviceDTOSet.add(serviceDTO);

        Booking booking = bookingService.creatrBooking(bookingRequest, userDTO, salonDTO, serviceDTOSet);

        return ResponseEntity.ok(booking);
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingsByCustomer(
    ) {
        List<Booking> booking = bookingService.getBookingByCustomer(1L);
        return ResponseEntity.ok(getBookingDTOs(booking));
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingsBySalon(
    ) {
        List<Booking> booking = bookingService.getBookingBySalon(1L);
        return ResponseEntity.ok(getBookingDTOs(booking));
    }

    private Set<BookingDTO> getBookingDTOs(List<Booking> booking) {
        return booking.stream().map(bookings -> {
            return BookingMapper.toDTO(bookings);
        }).collect(Collectors.toSet());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingsById(
            @PathVariable Long bookingId
    ) throws Exception {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }


    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status
    ) throws Exception {
        Booking booking =bookingService.updateBooking(bookingId, status);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity <List<BookingSlotDTO>> getBookedSlot(
            @PathVariable Long salonId,
            @RequestParam(required = false) LocalDate date
    ) throws Exception {
       List<Booking> booking = bookingService.getBookingByDate(date,salonId);

       List<BookingSlotDTO> slotDTOS=booking.stream()
               .map(bookings ->{
                   BookingSlotDTO slotDTO=new BookingSlotDTO();
                   slotDTO.setStartTime(bookings.getStartTime());
                   slotDTO.setEndTime(bookings.getEndTime());
                   return slotDTO;
                       }
                       ).collect(Collectors.toList());
        return ResponseEntity.ok(slotDTOS);
    }

    @GetMapping("/report")
    public ResponseEntity <SalonReport> getSalonReport(

    ) throws Exception {
       SalonReport  report= bookingService.getSalonReport(1L);
        return ResponseEntity.ok(report);
    }


}
