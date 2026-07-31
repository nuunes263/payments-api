package ursichDev.payments_api.dto;

import java.sql.Timestamp;

public record PaymentsRequestDTO(Double amount, Timestamp payment_date) {
}
