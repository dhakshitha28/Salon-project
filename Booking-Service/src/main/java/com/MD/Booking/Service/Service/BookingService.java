package com.MD.Booking.Service.Service;

import com.MD.Booking.Service.DTO.BookingRequest;
import com.MD.Booking.Service.DTO.SalonDTO;
import com.MD.Booking.Service.DTO.ServiceDTO;
import com.MD.Booking.Service.DTO.UserDTO;
import com.MD.Booking.Service.domain.BookingStatus;
import com.MD.Booking.Service.model.Booking;
import com.MD.Booking.Service.model.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

  Booking creatrBooking(BookingRequest booking,
                        UserDTO userDTO,
                        SalonDTO salonDTO,
                        Set<ServiceDTO> serviceDTOSet) throws Exception;

  List<Booking> getBookingByCustomer(Long customerId);
  List<Booking> getBookingBySalon(Long salonId);
  Booking getBookingById(Long id) throws Exception;
  Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
  List<Booking> getBookingByDate(LocalDate date, Long salonId);
  SalonReport getSalonReport(Long salonId);


}
