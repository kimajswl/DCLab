package com.example.dclab.bill;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BillController.class)
public class BillControllerTest {

    private MockMvc mvc;

    @MockBean
    private BillService billService;
    private BillRepository billRepository;

    @Test
    void getBillTest() throws Exception {
        BillForm formData = new BillForm(
                "visa",
                1000,
                "pictureName"
        );
        Bill bill = billService.create(formData,"uniqueName");

//        given(billService.getBillById(uid)).willReturn(Optional.of(bill));

        mvc.perform(get("/{bill_uid}", uid)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.uid", is(bill.getUid())));

        billRepository.hardDelete(bill.getUid());
    }

    @Test
    void uploadBillTest() throws Exception {
        Bill bill = new Bill.Builder().build();

//        given(billService.createBill(bill)).willReturn(bill);

        mvc.perform(post("/upload")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(bill)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.uid", is(bill.getUid())));
    }

    @Test
    public void deleteBillTest() throws Exception {
        // Given
        Bill bill = createBill();
        billRepository.save(bill);
        Long billId = bill.getUid();

        // When
        mock.perform(delete("/bills/" + billId))
            .andExpect(status().isOk());

        Optional<Bill> optionalBill = billRepository.findById(billId);

        // Then
        assertFalse(optionalBill.isPresent());
    }
}
