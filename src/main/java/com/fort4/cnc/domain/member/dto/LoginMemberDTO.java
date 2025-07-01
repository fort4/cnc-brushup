package com.fort4.cnc.domain.member.dto;

import com.fort4.cnc.domain.member.MemberEntity;

import lombok.Getter;

@Getter
public class LoginMemberDTO {
	
    private Long id;
    private String nickname;

    // 생성자
    public LoginMemberDTO(MemberEntity entity) {
        this.id = entity.getId();
        this.nickname = entity.getNickname();
    }
    
}
