package com.accenture.challenge.bank;

import com.accenture.challenge.bank.dto.BankDto;
import com.accenture.challenge.bank.entity.Bank;
import com.accenture.challenge.bank.exception.DuplicateResourceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class BankApplicationTests {

	@Test
	void contextLoads() {
	}

}
