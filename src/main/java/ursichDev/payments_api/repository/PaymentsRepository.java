package ursichDev.payments_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ursichDev.payments_api.model.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
}
