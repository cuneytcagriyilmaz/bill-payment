package com.evam.billpayment.controller;


import com.evam.billpayment.dto.BillDTO;
import com.evam.billpayment.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/bills")
public class BillController {

    private final BillService billService;

    public BillController(final BillService billService) {
        this.billService = billService;
    }

    //POST http://localhost:8080/bills
    @PostMapping
    public ResponseEntity<Long> createBill(@RequestBody @Valid final BillDTO billDTO) {
        return new ResponseEntity<>(billService.create(billDTO), HttpStatus.CREATED);
    }

    //GET http://localhost:8080/bills
    @GetMapping
    public ResponseEntity<List<BillDTO>> getAllBills() {
        return ResponseEntity.ok(billService.findAll());
    }

    //GET http://localhost:8080/bills/{{billId}}
    @GetMapping("/{billId}")
    public ResponseEntity<BillDTO> getBill(@PathVariable final Long billId) {
        return ResponseEntity.ok(billService.get(billId));
    }

    //GET http://localhost:8080/bills/user/{{userid}}
    @GetMapping("/user/{userid}")
    public ResponseEntity<List<BillDTO>> findAllByUser_UserId(@PathVariable Long userid){
        return ResponseEntity.ok(billService.getBillByUser(userid));
    }

    //PUT http://localhost:8080/bills/{{billId}}
    @PutMapping("/{billId}")
    public ResponseEntity<Void> updateBill(@PathVariable final Long billId,
                                           @RequestBody @Valid final BillDTO billDTO) {
        billService.update(billId, billDTO);
        return ResponseEntity.ok().build();
    }

    //DELETE http://localhost:8080/bills/{{billId}}
    @DeleteMapping("/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable final Long billId) {
        billService.delete(billId);
        return ResponseEntity.noContent().build();
    }

    //DELETE http://localhost:8080/bills/user/{{userid}}
    @DeleteMapping("/user/{userid}")
    public ResponseEntity<Void> deleteBillByUser(@PathVariable final Long userid) {
        billService.deleteBillByUser(userid);
        return ResponseEntity.noContent().build();
    }


}
