package ursichDev.payments_api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ursichDev.payments_api.dto.PaymentsRequestDTO;
import ursichDev.payments_api.mapper.PaymentsMapper;
import ursichDev.payments_api.model.Payments;
import ursichDev.payments_api.repository.PaymentsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;
    private final PaymentsMapper paymentsMapper;

    public List<Payments> findAll() {
        return paymentsRepository.findAll();
    }

    public Payments findById(Long id) {
        return paymentsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pagamento nao encontrado: " + id));
    }

    public Payments createPayments(PaymentsRequestDTO input) {
        return paymentsRepository.save(paymentsMapper.toEntity(input));
    }

    public Payments updatePayments(Long id, PaymentsRequestDTO input) {
        var payment = findById(id);

        paymentsMapper.updateEntityFromDto(input, payment);

        return paymentsRepository.save(payment);
    }

    public void deletePayments(Long id) {
        if (!paymentsRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pagamento nao encontrado: " + id);
        }

        paymentsRepository.deleteById(id);
    }
}
