package com.evam.billpayment.service;

import com.evam.billpayment.dto.BillDTO;
import com.evam.billpayment.entity.Bill;
import com.evam.billpayment.entity.User;
import com.evam.billpayment.repository.BillRepository;
import com.evam.billpayment.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BillService {

    private final BillRepository billRepository;
    private final UserRepository userRepository;

    public BillService(final BillRepository billRepository,
                       final UserRepository userRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    public List<BillDTO> findAll() {
        return billRepository.findAll(Sort.by("billId"))
                .stream()
                .map(bill -> mapToDTO(bill, new BillDTO()))
                .collect(Collectors.toList());
    }

    public BillDTO get(final Long billId) {
        return billRepository.findById(billId)
                .map(bill -> mapToDTO(bill, new BillDTO()))
                .orElseThrow(() -> new EntityNotFoundException("Bill is not found."));
    }

    public List<BillDTO> getBillByUser(final Long userId) {
        return billRepository.getBillByUser(userId).stream().map(bill -> mapToDTO(bill, new BillDTO()))
                .collect(Collectors.toList());
    }

    public void delete(final Long billId) {
        billRepository.deleteById(billId);
    }

    public void deleteBillByUser(final Long userId) {
        billRepository.getBillByUser(userId);
        billRepository.deleteBillByUser(userId);
    }

    public Long create(final BillDTO billDTO) {
        final Bill bill = new Bill();
        mapToEntity(billDTO, bill);
        return billRepository.save(bill).getBillId();
    }

    public void update(Long billId, BillDTO billDTO) {
        final Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new EntityNotFoundException("Bill is not found."));
        mapToEntity(billDTO, bill);
        billRepository.save(bill);
    }


    private BillDTO mapToDTO(final Bill bill, final BillDTO billDTO) {
        billDTO.setBillId(bill.getBillId());
        billDTO.setBillAmount(bill.getBillAmount());
        billDTO.setBillDate(bill.getBillDate());
        billDTO.setStatus(bill.getStatus());
        billDTO.setUser(bill.getUser() == null ? null : bill.getUser().getUserId());
        return billDTO;
    }

    private Bill mapToEntity(final BillDTO billDTO, final Bill bill) {
        bill.setBillAmount(billDTO.getBillAmount());
        bill.setBillDate(billDTO.getBillDate());
        bill.setStatus(billDTO.getStatus());
        final User user = billDTO.getUser() == null ? null : userRepository.findById(billDTO.getUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        bill.setUser(user);
        return bill;
    }

}
