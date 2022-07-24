package com.evam.billpayment.service;


import com.evam.billpayment.dto.UserDTO;
import com.evam.billpayment.entity.User;
import com.evam.billpayment.repository.BillRepository;
import com.evam.billpayment.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserRepository userRepository;

    private final BillService billService;

    public UserService(final UserRepository userRepository, BillRepository billRepository, BillService billService) {
        this.userRepository = userRepository;
        this.billService = billService;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll(Sort.by("userId"))
                .stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(final Long userId) {
        return userRepository.findById(userId)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getUserId();
    }

    public void update(final Long userId, final UserDTO userDTO) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long userId) {
        userRepository.deleteById(userId);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserLastName(user.getUserLastName());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setUserName(userDTO.getUserName());
        user.setUserLastName(userDTO.getUserLastName());
        return user;
    }
}
