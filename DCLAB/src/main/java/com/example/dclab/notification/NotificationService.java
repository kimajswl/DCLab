package com.example.dclab.notification;

import com.example.dclab.user.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService { // TODO. 공지사항 삭제, 수정 구현하기

    private final NotificationRepository notificationRepository;

    public void addNotification(NotificationForm notificationForm, UserForm userForm) {

        Notification notification = Notification.builder()
                .title(notificationForm.getTitle())
                .maintext(notificationForm.getMaintext())
                .username(userForm.getUsername())
                .localDateTime(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }



}
