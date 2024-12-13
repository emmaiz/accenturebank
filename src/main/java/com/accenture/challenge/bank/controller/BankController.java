package com.accenture.challenge.bank.controller;

import com.accenture.challenge.bank.dto.BankDto;
import com.accenture.challenge.bank.entity.Bank;
import com.accenture.challenge.bank.service.BankService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Bank> createBank(@RequestBody BankDto dto) {
        return new ResponseEntity<>(service.createBank(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        return ResponseEntity.ok(service.getAllBanks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBankById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable Long id, @RequestBody BankDto dto) {
        return ResponseEntity.ok(service.updateBank(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
        service.deleteBank(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/internal-consume")
    public ResponseEntity<List<Bank>> consumeGetAllBanks() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Bank>> response = restTemplate.exchange(
                "http://localhost:8080/api/banks",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return ResponseEntity.ok(response.getBody());
    }
}

