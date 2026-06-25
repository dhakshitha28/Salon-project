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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl  implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking creatrBooking(BookingRequest booking, UserDTO userDTO, SalonDTO salonDTO, Set<ServiceDTO> serviceDTOSet) throws Exception {

      int totalDuration= serviceDTOSet.stream().mapToInt(ServiceDTO::getDuration).sum();
      LocalDateTime startTime = booking.getStartTime();
      LocalDateTime endTime = startTime.plusMinutes(totalDuration);

      boolean isAvailable=isTimeSlotAvailable(salonDTO,startTime,endTime);

      int totalPrice=serviceDTOSet.stream().mapToInt(ServiceDTO::getPrice).sum();
      Set<Long> idList = serviceDTOSet.stream().map(ServiceDTO::getId).collect(java.util.stream.Collectors.toSet());
      Booking newBooking=new Booking();
      newBooking.setCustomerId(userDTO.getId());
      newBooking.setSalonId(salonDTO.getId());
      newBooking.setServiceIds(idList);
      newBooking.setStatus(BookingStatus.PENDING);
      newBooking.setStartTime(startTime);
      newBooking.setEndTime(endTime);

      return bookingRepository.save(newBooking);

    }


    public boolean isTimeSlotAvailable(SalonDTO salonDTO, LocalDateTime startTime, LocalDateTime endTime) throws Exception {

        List<Booking> existingBookings=getBookingBySalon(salonDTO.getId());

        LocalDateTime salonOpenTime=salonDTO.getOpeningTime().atDate(startTime.toLocalDate());
        LocalDateTime SalonCloseTime=salonDTO.getClosingTime().atDate(endTime.toLocalDate());

        if(startTime.isBefore(salonOpenTime) || endTime.isAfter(SalonCloseTime)) {
            throw new IllegalArgumentException("Booking time is outside of salon operating hours.");
        }

        for(Booking existingBooking:existingBookings) {
            LocalDateTime existingStartTime=existingBooking.getStartTime();
            LocalDateTime existingEndTime=existingBooking.getEndTime();
            if(startTime.isBefore(existingEndTime)
            && endTime.isAfter(existingStartTime)) {

                throw new Exception("Slot is not available for booking.");
            }

            if(startTime.isEqual(existingStartTime)||endTime.isEqual(existingEndTime)) {
                throw new Exception("Booking time overlaps with an existing booking.");
            }
        }
        return  true;

    }

    @Override
    public List<Booking> getBookingByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking=bookingRepository.findById(id).orElse(null);
        if(booking==null) {
            throw new IllegalArgumentException("Booking not found.");
        }

        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking=getBookingById(bookingId);

        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingByDate(LocalDate date, Long salonId) {
        List<Booking>  allBookings=getBookingBySalon(salonId);

        if(date==null) {
            return allBookings;
        }

        return allBookings.stream().filter(booking -> isSameDate(booking.getStartTime(),date)||
                isSameDate(booking.getEndTime(),date)).collect(Collectors.toList());

    }

    private boolean isSameDate(LocalDateTime startTime, LocalDate date) {

        return startTime.toLocalDate().isEqual(date);

    }

    @Override
    public SalonReport getSalonReport(Long salonId) {

        List<Booking> booking=getBookingBySalon(salonId);
        int totalEarnings=booking.stream().mapToInt(Booking::getTotalPrice).sum();
        Integer totalBooking=booking.size();
        List<Booking> cancelBooking=booking.stream().filter(bookings ->bookings.getStatus().equals(BookingStatus.CANCELLED) ).collect(Collectors.toList());

        Double totalRefund=cancelBooking.stream().mapToDouble(Booking::getTotalPrice).sum();

        SalonReport salonReport=new SalonReport();
        salonReport.setSalonId(salonId);
        salonReport.setCancelledBookings(cancelBooking.size());
        salonReport.setTotalBookings(totalEarnings);
        salonReport.setTotalEarnings(totalEarnings);
        salonReport.setTotalRefund(totalRefund);
        salonReport.setTotalBookings(totalBooking);
        return salonReport;
    }
}
