package ursichDev.payments_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ursichDev.payments_api.dto.PaymentsRequestDTO;
import ursichDev.payments_api.model.Payments;
import ursichDev.payments_api.services.PaymentsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentsController {

    private final PaymentsService paymentsService;

    @GetMapping()
    public ResponseEntity<List<Payments>> findAllPayments(){
        return ResponseEntity.ok(paymentsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payments> findPaymentsById(@PathVariable Long id){
        return ResponseEntity.ok(paymentsService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Payments> createPayments(@RequestBody PaymentsRequestDTO input){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentsService.createPayments(input));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payments> updatePayments(@PathVariable Long id,
                                                   @RequestBody PaymentsRequestDTO input){
        return ResponseEntity.ok(paymentsService.updatePayments(id, input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayments(@PathVariable Long id){
        paymentsService.deletePayments(id);

        return ResponseEntity.noContent().build();
    }
}
