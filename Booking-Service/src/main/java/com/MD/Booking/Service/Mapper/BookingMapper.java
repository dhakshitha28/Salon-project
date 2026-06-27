package com.MD.Booking.Service.Mapper;

import com.MD.Booking.Service.DTO.BookingDTO;
import com.MD.Booking.Service.model.Booking;

public class BookingMapper {

    public static BookingDTO toDTO (Booking booking){
        BookingDTO bookingDTO =new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setServiceIds(bookingDTO.getServiceIds());

        return bookingDTO;

    }
}
