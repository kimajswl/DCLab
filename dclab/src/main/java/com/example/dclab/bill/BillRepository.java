package com.example.dclab.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill save(Bill bill);

    @Query("DELETE FROM Bill as b WHERE b.uid = :id")
    void hardDelete(Long id);
}
