package com.example.dclab.bill;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;

    public Bill create(BillForm formData, String uniqueName) {
        Bill bill = Bill.builder()
            .timestamp(formData.getTimestmap())
            .cardName(formData.getCardName())
            .pictureName(formData.getPictureName())
            .picturePath(uniqueName)
            .build();
        return billRepository.save(bill);
    }

    public boolean delete(Long uid) {
        try {
            billRepository.deleteById(uid);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public List<BillDto> getAllBills(int page, int size) {
        return billRepository
                .findAll(PageRequest.of(page, size))
                .map(BillDto::fromEntity)
                .toList();
    }


}
