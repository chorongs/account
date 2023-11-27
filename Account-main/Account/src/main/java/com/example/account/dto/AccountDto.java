package com.example.account.dto;

import java.time.LocalDateTime;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.type.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {

	private Long userId;
	private String accountNumber;
	private Long balance;
	
	private LocalDateTime registeredAt;
	private LocalDateTime unRegisteredAt;
	
	// fromEntity : 특정 Eneity에서 특정 Dto로 반환할 때 생성자를 사용하지 않고, 
	//				static 메서드를 사용하여 좀 더 깔끔하게 구현할 수 있음
	public static AccountDto fromEntity(Account account) {
		
		return AccountDto.builder()
				.userId(account.getAccountUser().getId())
				.accountNumber(account.getAccountNumber())
				.registeredAt(account.getRegisteredAt())
				.unRegisteredAt(account.getUnRegisteredAt())
				.build();
				
	}
}
