package com.MD.payment.service.Service;

import com.MD.payment.service.DTO.BookingDTO;
import com.MD.payment.service.DTO.UserDTO;
import com.MD.payment.service.domain.PaymentMethod;
import com.MD.payment.service.model.PaymentOrder;
import com.MD.payment.service.payLoad.PaymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod) throws RazorpayException;
    PaymentOrder getPaymentOrderById(Long id) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String paymentId);
    PaymentLink createRazorpayPaymentLink(UserDTO user, Long amt,Long orderId) throws RazorpayException;
    String createStripePaymentLink(UserDTO user, Long amt,Long orderId);



}
