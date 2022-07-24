package com.evam.billpayment.controller;

import com.evam.billpayment.dto.PaymentDTO;
import com.evam.billpayment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    //POST http://localhost:8080/payments
    @PostMapping
    public ResponseEntity<Long> createPayment(@RequestBody @Valid final PaymentDTO paymentDTO) {
        return new ResponseEntity<>(paymentService.create(paymentDTO), HttpStatus.CREATED);
    }

    //GET http://localhost:8080/payments
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    //GET http://localhost:8080/payments/{{paymentId}}
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable final Long paymentId) {
        return ResponseEntity.ok(paymentService.get(paymentId));
    }

    //PUT http://localhost:8080/payments/{{paymentId}}
    @PutMapping("/{paymentId}")
    public ResponseEntity<Void> updatePayment(@PathVariable final Long paymentId,
                                              @RequestBody @Valid final PaymentDTO paymentDTO) {
        paymentService.update(paymentId, paymentDTO);
        return ResponseEntity.ok().build();
    }

    //DELETE http://localhost:8080/payments/{{paymentId}}
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable final Long paymentId) {
        paymentService.delete(paymentId);
        return ResponseEntity.noContent().build();
    }

}
