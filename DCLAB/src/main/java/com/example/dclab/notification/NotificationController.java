package com.example.dclab.notification;


import com.example.dclab.user.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/notification")
    public String notificationForm() {
        return "/notification";
    }

    @PostMapping("/addNotification")
    public String addNotification(NotificationForm notificationForm, UserForm userForm) {
        notificationService.addNotification(notificationForm, userForm);
        return "/notification";

    }

    @GetMapping("/deleteNotification")
    public String deleteNotification(Long id) {
        notificationService.deleteNotification(id);
        return "/notification";
    }



}
