package com.example.account.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CreateAccount {
	
	@Getter
	@Setter
	public static class Request {
		@NotNull
		@Min(1) // userId는 최소 1부터 시작
		private Long userId;
		
		@NotNull 
		@Min(0) // initialBalance의 최솟값은 0(원)
		private Long initialBalance;
		
	}
	
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Response {
		private Long userId;
		private String accountNumber;
		private LocalDateTime registeredAt;
		
		public static Response from(AccountDto accountDto) {
			return Response.builder()
					.userId(accountDto.getUserId())
					.accountNumber(accountDto.getAccountNumber())
					.registeredAt(accountDto.getRegisteredAt())
					.build();
		}
	}
}
