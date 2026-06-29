package com.MD.payment.service.DTO;

import com.MD.Booking.Service.model.Booking;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingRequest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<Booking> serviceIds;


}
