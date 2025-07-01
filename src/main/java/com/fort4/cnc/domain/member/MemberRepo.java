package com.fort4.cnc.domain.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<MemberEntity, Long> {
	
	Optional<MemberEntity> findByNickname(String nickname);
}
