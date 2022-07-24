package com.evam.billpayment.repository;

import com.evam.billpayment.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query(value = "SELECT new com.evam.billpayment.entity.Bill( b.billId, b.billAmount, b.billDate, b.status, b.user) from Bill as b  WHERE b.user.userId = :userId ")
    List<Bill> getBillByUser(Long userId);

    /*
        @Query(value = "SELECT * from bill WHERE user_id = ?",nativeQuery = true)
        List<Bill> getBillByUser(Long userId);
     */

    //List<Bill> getBillByUserUserId(Long userId);

    //List<Bill> findAllByUser_UserId(Long userId);

    //@Query(value = "SELECT * from bill WHERE user_id = ?",nativeQuery = true)


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM bill WHERE user_id = ?",nativeQuery = true)
    void deleteBillByUser(@Param("userId")Long userId);


}
