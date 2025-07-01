package com.fort4.cnc.domain.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fort4.cnc.domain.member.dto.MemberDTO;

@Service
public class MemberService 
{
	
	@Autowired
	private MemberRepo memberRepo;
	
    /** 회원가입 서비스 로직 */
    public boolean register(MemberDTO dto) 
    {
        if (isDuplicated(dto)) return false;

        MemberEntity member = new MemberEntity();
        member.setPassword(dto.getPassword());
        member.setNickname(dto.getNickname());

        memberRepo.save(member);
        return true;
    }
    
    /** 닉네임 중복 검사 메서드 (회원가입시 사용중) */
    private boolean isDuplicated(MemberDTO dto)
    {
        return memberRepo.findByNickname(dto.getNickname()).isPresent();
    }
	
    /** 로그인 서비스 로직 */
	public MemberEntity login(String nickname, String password) 
	{
		Optional<MemberEntity> optional = memberRepo.findByNickname(nickname);
		
		boolean valid = optional.isPresent() && optional.get().getPassword().equals(password);
		
		if (valid) {
			return optional.get();
		}
		
		return null;
	}
	
}
