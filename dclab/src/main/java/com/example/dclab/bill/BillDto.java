package com.example.dclab.bill;

import lombok.Data;
import lombok.NonNull;

@Data
public class BillDto {
    @NonNull private int amount;
    @NonNull private String cardName;
    @NonNull private String pictureName;

    public static BillDto fromEntity(Bill bill) {
        return new BillDto(bill.getAmount(), bill.getCardName(), bill.getPictureName());
    }
}