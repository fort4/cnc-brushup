package com.fort4.cnc.domain.member;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter
public class MemberEntity {
	
	// GeneratedValue == 값을 어떻게 생성할지를 지정
	// strategy = GenerationType.IDENTITY == MySQL의 A_I를 사용해서 pk 자동 생성.
	// 즉 insert 시에 id값 없어도 db가 알아서 숫자를 증가시켜줌
	// 명시하지 않은 경우 GenerationType.AUTO라 Hibernate가 명확한 전략을 모르면 오류가 생길 수 있으니 명시함.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Column을 명시적으로 설정. 
	// 없이 기본 설정만 쓸 경우 JPA가 적당히 처리해줌. 무조건 명시하는게 실무에서도 좋을듯
//	@Column(nullable = false, unique = true)
//	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false, unique = true)
	private String nickname;
	
	private String role;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	// 소프트 딜리트용 컬럼
	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;
	
}
