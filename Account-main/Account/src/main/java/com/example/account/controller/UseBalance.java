package com.example.account.controller;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.account.type.TransactionResultType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UseBalance {
	
	@Getter
	@Setter
	public static class Request {
		@NotNull
		@Min(1) // userId는 최소 1부터 시작
		private Long userId;
		
		@NotBlank
		@Size(min = 10, max = 10)
		private String accountNumber;
		
		@NotNull 
		@Min(10)
		@Max(1000_000_000)
		private Long amont;
		
	}
	
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Response {
		private String accountNumber;
		private TransactionResultType transactionResult;
		private String transactionId;
		private Long amount;
		private LocalDateTime transactedAt;
		

	}
}
