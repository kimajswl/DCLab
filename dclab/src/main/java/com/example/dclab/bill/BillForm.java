package com.example.dclab.bill;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BillForm {
    private String cardName;
    private Integer amount;
    private String pictureName;
    private LocalDateTime timestmap;

    public BillForm(
            String cardName,
            Integer amount,
            String pictureName
    ) {
        this.cardName = cardName;
        this.amount = amount;
        this.pictureName = pictureName;
        this.timestmap = LocalDateTime.now();
    }
}
