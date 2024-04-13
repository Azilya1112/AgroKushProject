package com.example.agrokushproject.repositories;

import com.example.agrokushproject.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
