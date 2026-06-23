package com.MD.Booking.Service.Service.Impl;


import com.MD.Booking.Service.DTO.BookingRequest;
import com.MD.Booking.Service.DTO.SalonDTO;
import com.MD.Booking.Service.DTO.ServiceDTO;
import com.MD.Booking.Service.DTO.UserDTO;
import com.MD.Booking.Service.Repository.BookingRepository;
import com.MD.Booking.Service.Service.BookingService;
import com.MD.Booking.Service.domain.BookingStatus;
import com.MD.Booking.Service.model.Booking;
import com.MD.Booking.Service.model.SalonReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl  implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking creatrBooking(BookingRequest booking, UserDTO userDTO, SalonDTO salonDTO, Set<ServiceDTO> serviceDTOSet) {

      int totalDuration= serviceDTOSet.stream().mapToInt(ServiceDTO::getDuration).sum();
      LocalDateTime startTime = booking.getStartTime();
      LocalDateTime endTime = startTime.plusMinutes(totalDuration);

    }

    public boolean isTimeSlotAvailable(SalonDTO salonDTO, LocalDateTime startTime, LocalDateTime endTime) {

        LocalDateTime salonOpenTime=salonDTO.getOpeningTime().atDate(startTime.toLocalDate());
        LocalDateTime SalonCloseTime=salonDTO.getClosingTime().atDate(endTime.toLocalDate());

        if(startTime.isBefore(salonOpenTime) || endTime.isAfter(SalonCloseTime)) {
            throw new IllegalArgumentException("Booking time is outside of salon operating hours.");
        }

        return  true;

    }

    @Override
    public List<Booking> getBookingByCustomer(Long customerId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingBySalon(Long salonId) {
        return List.of();
    }

    @Override
    public Booking getBookingById(Long id) {
        return null;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) {
        return null;
    }

    @Override
    public List<Booking> getBookingByDate(LocalDate date, Long salonId) {
        return List.of();
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        return null;
    }
}
