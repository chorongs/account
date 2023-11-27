package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.dto.AccountDto;
import com.example.account.exception.AccountExept;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.AccountUserRepository;
import com.example.account.type.AccountStatus;
import com.example.account.type.ErrorCode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.account.type.AccountStatus.IN_USE;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 꼭 필요한 arg 가 들어간 생성자를만들어줌
public class AccountService {
    private final AccountRepository accountRepository; // final로 선언했을 때, 생성자가 아닌 이상 담아줄 수 없음
    private final AccountUserRepository accountUserRepository;

    /**
	 * 사용자가 있는지 조회
	 * 계좌의 번호를 생성하고
	 * 계좌를 저장하고, 그 정보를 넘긴다.
     * @param userId
     * @param initialBalance
     */
    @Transactional
    public AccountDto createAccount(Long userId, Long initialBalance) {
    	// 사용자 찾기
    	// 없을때는 errorCode 
    	AccountUser accountUser = accountUserRepository.findById(userId)
    			.orElseThrow(() -> new AccountExept(ErrorCode.USER_NOT_FOUND));
    	
    	// 사용자가 있을때, 가장 최근에 생성된 계좌를 조회
    	String newAccountNumber = accountRepository.findFirstByOrderByIdDesc()
    			.map(account -> (Integer.parseInt(account.getAccountNumber())) + 1 + "")
    			.orElse("1000000000");// 가장 최근에 생성된 계좌정보 가져오기
    	
    	
    	// 1,2 에서 찾은 정보를 바탕으로 정보를 builder로 저장한 후에 리턴
    	return AccountDto.fromEntity(
    			accountRepository.save(
		    		Account.builder()
					.accountUser(accountUser)
					.accountStatus(IN_USE)
					.accountNumber(newAccountNumber)
					.balance(initialBalance)
					.registeredAt(LocalDateTime.now())
					.build()
					)
    			);

    }


	@Transactional
    public Account getAccount(Long id) {
        if(id < 0){
            throw new RuntimeException("Minus");
        }
        return accountRepository.findById(id).get();
    }


	@Transactional
	public AccountDto deleteAccount(Long userId, String accountNumber) {
    	// 사용자 찾기
    	// 없을때는 errorCode 
    	AccountUser accountUser = accountUserRepository.findById(userId)
    			.orElseThrow(() -> new AccountExept(ErrorCode.USER_NOT_FOUND));
    	
    	Account account = accountRepository.findByAccountNumber(accountNumber)
    			.orElseThrow(() -> new AccountExept(ErrorCode.ACCOUNT_NOT_FOUND));
    	
    	validateDeleteAccount(accountUser, account);
    	
    	account.setAccountStatus(AccountStatus.UNREGISTERED);
    	account.setUnRegisteredAt(LocalDateTime.now()); // 현재시간으로 수정해줌
    	
    	accountRepository.save(account);
    	
    	return AccountDto.fromEntity(account);

	}


	private void validateDeleteAccount(AccountUser accountUser, Account account) {
		if (!Objects.equals(accountUser.getId(), account.getAccountUser().getId())) {
			throw new AccountExept(ErrorCode.USER_ACCOUNT_UN_MATCH);
		}
		
		if (account.getAccountStatus() == AccountStatus.UNREGISTERED) {
			throw new AccountExept(ErrorCode.ACCOUNT_ALREADY_UNREGISTERED);
		}
		
		if (account.getBalance() > 0) {
			throw new AccountExept(ErrorCode.BALANCE_NOT_EMPTY);
		}
		
		
	}


	public List<AccountDto> getAccountByUserId(Long userId) {
		AccountUser accountUser = accountUserRepository.findById(userId)
				.orElseThrow(() -> new AccountExept(ErrorCode.USER_NOT_FOUND));
    	
		List<Account> accounts = accountRepository.
				findByAccountUser(accountUser);
		
		return accounts.stream()
				.map(AccountDto::fromEntity)
				.collect(Collectors.toList());

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
