package com.example.dclab.notification;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationForm { // 공지사항 폼

    @Column(length = 50)
    private String title;

    @Column(length = 500)
    private String maintext;
}
