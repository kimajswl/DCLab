package com.example.dclab.bill;

import com.example.dclab.config.Enviroment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("bills")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    @GetMapping
    public ResponseEntity<List<BillDto>> getAllBills(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        List<BillDto> bills = billService.getAllBills(page, size);
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBill(
            @RequestParam("json") BillForm formData,
            @RequestParam("file") MultipartFile bill
        ) {
        if (bill.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }
        if (!isImageFile(bill)) {
            return new ResponseEntity<>("File is not image format", HttpStatus.BAD_REQUEST);
        }

        // Save File
        String fileName = bill.getOriginalFilename();
        String uuidFileName = generateUniqueFileName(fileName);
        Path path = Paths.get(Enviroment.getSavePath("bills"), uuidFileName);
        try {
            Files.copy(bill.getInputStream(), path);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        formData.setPictureName(fileName);
        billService.create(formData, uuidFileName);

        return new ResponseEntity<>(fileName + "," + uuidFileName, HttpStatus.OK);
    }

    @DeleteMapping("/{billUid}")
    public void deleteBill(@PathVariable("billUid") Long billUid) {
        //soft delete
        billService.delete(billUid);
    }

    private String generateUniqueFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = uuid + extension;
        return uniqueFileName;
    }

    private boolean isImageFile(MultipartFile file) {
        try {
            String contentType = file.getContentType();
            return contentType.startsWith("image");
        } catch (Exception e) {
            return false;
        }
    }
}
