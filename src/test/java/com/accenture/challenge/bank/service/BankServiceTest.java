package com.accenture.challenge.bank.service;


import com.accenture.challenge.bank.dto.BankDto;
import com.accenture.challenge.bank.entity.Bank;
import com.accenture.challenge.bank.exception.DuplicateResourceException;
import com.accenture.challenge.bank.repository.BankRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    private BankRepository repository;

    @InjectMocks
    private BankService service;

    @Test
    void testCreateBank_ThrowsDuplicateResourceException() {
        BankDto dto = new BankDto();
        dto.setName("Test Bank");

        when(repository.findByName(dto.getName())).thenReturn(Optional.of(new Bank()));

        assertThrows(DuplicateResourceException.class, () -> service.createBank(dto));
    }
}