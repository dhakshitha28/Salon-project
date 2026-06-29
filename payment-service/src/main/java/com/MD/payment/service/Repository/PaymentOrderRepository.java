package com.MD.payment.service.Repository;

import com.MD.payment.service.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {


    PaymentOrder findByPaymentLinkId(String paymentLinkId);

}
