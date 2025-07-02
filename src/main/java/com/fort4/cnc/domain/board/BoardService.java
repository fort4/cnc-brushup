package com.fort4.cnc.domain.board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fort4.cnc.domain.member.MemberEntity;
import com.fort4.cnc.domain.member.MemberRepo;

@Service
public class BoardService {

    @Autowired
    private BoardRepo boardRepo;
    @Autowired
    private MemberRepo memberRepo;

    // 게시글 저장
    public void save(BoardDTO dto) {
        MemberEntity writer = memberRepo.findById(dto.getWriterId())
                .orElseThrow(() -> new NoSuchElementException("작성자 없음"));
        
        BoardEntity entity = new BoardEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setWriter(writer);
        entity.setCreatedAt(LocalDateTime.now());

        boardRepo.save(entity);
    }

    // 게시글 목록 조회
    public List<BoardDTO> listAll() {
        List<BoardEntity> entityList = boardRepo.findAllWithWriter(); // 페치조인
        
        // 1. stream() == entityList를 하나씩 꺼내서 처리할 수 있게 stream으로 바꿈
        // 2. map(entity -> {}) == 각 요소를 다른 형태로 바꾸는 함수
        // 3. collectors.toList() == .map() 결과를 전부 모아서 List<BoardDTO>로 만들어 주는 역할
        //   -> 즉 스트림 -> 리스트로 되돌리는 과정
        return entityList.stream().map(entity -> {
            BoardDTO dto = new BoardDTO();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setWriterId(entity.getWriter().getId());
            dto.setWriterNickname(entity.getWriter().getNickname());
            dto.setCreatedAt(entity.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
    }
    
    // 게시글 상세 보기
    public BoardDTO detailById(Long id) {
        BoardEntity entity = boardRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글 없음"));

        BoardDTO dto = new BoardDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setWriterId(entity.getWriter().getId());
        dto.setWriterNickname(entity.getWriter().getNickname());
        dto.setCreatedAt(entity.getCreatedAt());
        
        return dto;
    }
    
    // 게시글 삭제
    public void deleteById(Long id)
    {
    	if (!boardRepo.existsById(id))
    	{
    		throw new NoSuchElementException("존재하지 않는 게시글 입니다.");
    	}
    	boardRepo.deleteById(id);
    }


}

