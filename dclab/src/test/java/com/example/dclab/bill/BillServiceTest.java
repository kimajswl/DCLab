package com.example.dclab.bill;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class BillServiceTest {
    @Autowired
    private BillService billService;
    private BillRepository billRepository;

    @Test
    public void testService() {
        BillForm formData = new BillForm(
            "visa",
            1000,
            "pictureName"
        );
        Bill bill = billService.create(formData,"uniqueName");

        // created OK
        Assertions.assertNotNull(bill);
        Assertions.assertNotNull(bill.getUid());
        Assertions.assertEquals("visa", bill.getCardName());
        Assertions.assertEquals(" picturePath", bill.getPicturePath());
        Assertions.assertEquals(" pictureName", bill.getPictureName());

        billService.delete(bill.getUid());

        // deleted OK
        Assertions.assertNull(bill.getCardName());
        Assertions.assertNull(bill.getPicturePath());
        Assertions.assertNull(bill.getPictureName());

        // hard delete test data
        billRepository.hardDelete(bill.getUid());
    }
}
