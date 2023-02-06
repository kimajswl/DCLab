package com.example.dclab.notification;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity // DB에 이렇게 저장하겠다는 의미
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification { // 공지사항

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String maintext;

    private String username; // 글을 쓴 사람 정보

    private LocalDateTime localDateTime; // 쓴 시간

}
