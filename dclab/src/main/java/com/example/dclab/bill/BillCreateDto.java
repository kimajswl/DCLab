package com.example.dclab.bill;

import lombok.Data;

@Data
public class BillCreateDto {
    private String cardName;
    private String pictureName;
    private String picturePath;

    public Bill toEntity() {
        return Bill.builder()
                .cardName(cardName)
                .pictureName(pictureName)
                .picturePath(picturePath)
                .build();
    }
}
