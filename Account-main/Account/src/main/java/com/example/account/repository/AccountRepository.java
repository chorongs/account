package com.example.account.repository;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findFirstByOrderByIdDesc(); // ID를 Desc(역순) 정렬 후 가장 첫번째 값 가져오기 // Optional<Account> : 데이터가 하나도 없을떄 처리
	
	Integer countByAvvountUser(AccountUser accountUser);
	
	Optional<Account> findByAccountNumber(String AccountNumber);
	
	List<Account> findByAccountUser(AccountUser accountUser);
	
}
