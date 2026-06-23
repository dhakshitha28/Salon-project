package com.MD.Booking.Service.Repository;

import com.MD.Booking.Service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerId(Long customerId);
    List<Booking>  findBySalonId(Long salonId);
}
