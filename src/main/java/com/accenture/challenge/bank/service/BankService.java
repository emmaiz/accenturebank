package com.accenture.challenge.bank.service;

import com.accenture.challenge.bank.dto.BankDto;
import com.accenture.challenge.bank.entity.Bank;
import com.accenture.challenge.bank.exception.DuplicateResourceException;
import com.accenture.challenge.bank.exception.ResourceNotFoundException;
import com.accenture.challenge.bank.repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final BankRepository repository;

    public BankService(BankRepository repository) {
        this.repository = repository;
    }

    public Bank createBank(BankDto dto) {
        if (repository.findByName(dto.getName()).isPresent()) {
            throw new DuplicateResourceException("Bank with the same name already exists.");
        }
        Bank bank = new Bank();
        bank.setName(dto.getName());
        bank.setCountry(dto.getCountry());
        bank.setSwiftCode(dto.getSwiftCode());
        bank.setFoundedYear(dto.getFoundedYear());
        return repository.save(bank);
    }

    public List<Bank> getAllBanks() {
        return repository.findAll();
    }

    public Bank getBankById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bank not found."));
    }

    public Bank updateBank(Long id, BankDto dto) {
        Bank bank = getBankById(id);
        bank.setName(dto.getName());
        bank.setCountry(dto.getCountry());
        bank.setSwiftCode(dto.getSwiftCode());
        bank.setFoundedYear(dto.getFoundedYear());
        return repository.save(bank);
    }

    public void deleteBank(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Bank not found.");
        }
        repository.deleteById(id);
    }
}