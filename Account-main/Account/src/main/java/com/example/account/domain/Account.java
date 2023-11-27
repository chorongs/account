package com.example.account.domain;

import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.account.type.AccountStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class) //자동으로 업데이트를 위한 설정
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private AccountUser accountUser;
    private String accountNumber;
    
    @Enumerated(EnumType.STRING) // db 테이블 숫자가 아닌 문자 그대로 저장되도록
    private AccountStatus accountStatus;
    private Long balance;
    
    
	private LocalDateTime registeredAt;
	private LocalDateTime unRegisteredAt;
	
	@CreatedDate //자동으로 업데이트
	private LocalDateTime createdAt;
	
	@LastModifiedDate //자동으로 업데이트
	private LocalDateTime updatedAt;
	
	
}
