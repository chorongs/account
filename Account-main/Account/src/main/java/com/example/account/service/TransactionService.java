package com.example.account.service;



import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.exception.AccountExept;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.AccountUserRepository;
import com.example.account.repository.TransactionRepository;
import com.example.account.type.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository transactionRepository;
	private final AccountUserRepository accountUserRepository;
	
	@Transactional
	public TransactionDto useBalance(Long userId, String accountNumber,Long amount) {
		
		AccountUser user = accountUserRepository.findById(userId)
				.orElseThrow(() -> new AccountExept(ErrorCode.USER_NOT_FOUND));
		

		
		return null;
		
	}


}
