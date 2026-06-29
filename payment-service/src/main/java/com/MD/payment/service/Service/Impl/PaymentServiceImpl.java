package com.MD.payment.service.Service.Impl;

import com.MD.payment.service.DTO.BookingDTO;
import com.MD.payment.service.DTO.UserDTO;
import com.MD.payment.service.Repository.PaymentOrderRepository;
import com.MD.payment.service.Service.PaymentService;
import com.MD.payment.service.domain.PaymentMethod;
import com.MD.payment.service.model.PaymentOrder;
import com.MD.payment.service.payLoad.PaymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentOrderRepository paymentOrderRepository;

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorpayApiSecretKey;

    @Override
    public PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod) throws RazorpayException {
        Long amt=(long)booking.getTotalPrice();
        PaymentOrder order=new PaymentOrder();
        order.setAmount(amt);
        order.setPaymentMethod(paymentMethod);
        order.setBookingId(booking.getId());
        order.setSalonId(booking.getSalonId());
        PaymentOrder savedOrder=paymentOrderRepository.save(order);

        PaymentLinkResponse paymentLinkResponse=new PaymentLinkResponse();
        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
            PaymentLink payment=createRazorpayPaymentLink(user, savedOrder.getAmount(),savedOrder.getId());
            String paymentUrl=payment.get("Short_Url");
            String paymentUrlId=payment.get("id");
            paymentLinkResponse.setPayment_link_url(paymentUrl);
            paymentLinkResponse.setGetPayment_link_id(paymentUrlId);
            savedOrder.setPaymentLinkId(paymentUrlId);
            paymentOrderRepository.save(savedOrder);
        }
        else{
            String paymentUrl = createStripePaymentLink(user, savedOrder.getAmount(),savedOrder.getId());
            paymentLinkResponse.setGetPayment_link_id(paymentUrl);

        }
        return paymentLinkResponse;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {

        PaymentOrder paymentOrder=paymentOrderRepository.findById(id).orElse(null);

        if(paymentOrder==null){
            throw new Exception("payment order not found");

        }
        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return paymentOrderRepository.findByPaymentLinkId(paymentId);
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO user, Long amt, Long orderId) throws RazorpayException {

        Long amount=amt*100;


            RazorpayClient razorpayClient=new RazorpayClient(razorpayApiKey,razorpayApiSecretKey);
            JSONObject paymentLinkRequest=new JSONObject();
            paymentLinkRequest.put("amount",amount);
            paymentLinkRequest.put("currency","INR");

            JSONObject customer=new JSONObject();
            customer.put("name",user.getFullName());
            customer.put("email",user.getEmail());

            paymentLinkRequest.put("customer",customer);

            JSONObject notify=new JSONObject();
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);

            paymentLinkRequest.put("reminder_enable",true);

            paymentLinkRequest.put("callBack_Url","http://localhost:300/payment-success/"+orderId);

            paymentLinkRequest.put("callBack_method","get");

            PaymentLink paymentLink=razorpay.paymentLink.create
            return null;
    }

    @Override
    public String createStripePaymentLink(UserDTO user, Long amt, Long orderId) {
        return "";
    }
}
