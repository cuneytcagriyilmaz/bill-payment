package com.evam.billpayment.service;

import com.evam.billpayment.dto.BillDTO;
import com.evam.billpayment.dto.PaymentDTO;
import com.evam.billpayment.entity.Payment;
import com.evam.billpayment.entity.User;
import com.evam.billpayment.repository.PaymentRepository;
import com.evam.billpayment.repository.UserRepository;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final BillService billService;

    public PaymentService(final PaymentRepository paymentRepository,
                          final UserRepository userRepository, BillService billService) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.billService = billService;
    }

    public List<PaymentDTO> findAll() {
        return paymentRepository.findAll(Sort.by("paymentId"))
                .stream()
                .map(payment -> mapToDTO(payment, new PaymentDTO()))
                .collect(Collectors.toList());
    }

    public PaymentDTO get(final Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> mapToDTO(payment, new PaymentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final PaymentDTO paymentDTO) {

        Payment payment = new Payment();
        double totalAmount = billService.findAll().stream().mapToDouble(BillDTO::getBillAmount).sum();
        paymentDTO.setTotalAmount(totalAmount);
        /*
        DoubleStream totalAmount =billService.findAll().stream().mapToDouble(billDTO::getBillAmount);
        paymentDTO.setTotalAmount(totalAmount.sum());
        */
        mapToEntity(paymentDTO, payment);
        return paymentRepository.save(payment).getPaymentId();
    }

    public void update(final Long paymentId, final PaymentDTO paymentDTO) {
        final Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(paymentDTO, payment);
        paymentRepository.save(payment);
    }

    public void delete(final Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    private PaymentDTO mapToDTO(final Payment payment, final PaymentDTO paymentDTO) {
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setTotalAmount(payment.getTotalAmount());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        paymentDTO.setUser(payment.getUser() == null ? null : payment.getUser().getUserId());
        return paymentDTO;
    }

    private Payment mapToEntity(final PaymentDTO paymentDTO, final Payment payment) {
        payment.setTotalAmount(paymentDTO.getTotalAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        final User user = paymentDTO.getUser() == null ? null : userRepository.findById(paymentDTO.getUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        payment.setUser(user);
        return payment;
    }

}
