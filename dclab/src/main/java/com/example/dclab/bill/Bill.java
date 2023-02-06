package com.example.dclab.bill;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "bill")
@SQLDelete(sql = "UPDATE bill SET delete_at = NOW() WHERE UID = ?")
@Where(clause = "delete_at IS NULL")
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long uid;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamp;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    private String cardName;
    @Column(nullable = false)
    private String picturePath;
    @Column(nullable = false)
    private String pictureName;
    private LocalDateTime deleteAt;

}
