package com.fort4.cnc.domain.board.comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fort4.cnc.domain.board.BoardEntity;
import com.fort4.cnc.domain.board.BoardRepo;
import com.fort4.cnc.domain.member.MemberEntity;
import com.fort4.cnc.domain.member.MemberRepo;

@Service
public class BoardCommentService {

    @Autowired private BoardCommentRepo commentRepo;
    @Autowired private BoardRepo boardRepo;
    @Autowired private MemberRepo memberRepo;
    
    // 댓글 저장
    public void save(BoardCommentDTO dto) {
        BoardEntity board = boardRepo.findById(dto.getBoardId()).orElseThrow();
        MemberEntity writer = memberRepo.findById(dto.getWriterId()).orElseThrow();

        BoardCommentEntity entity = new BoardCommentEntity();
        entity.setBoard(board);
        entity.setWriter(writer);
        entity.setContent(dto.getContent());
        entity.setCreatedAt(LocalDateTime.now());

        commentRepo.save(entity);
    }
    
    // 
    public List<BoardCommentDTO> findByBoardId(Long boardId) {
        BoardEntity board = boardRepo.findById(boardId).orElseThrow();
        return commentRepo.findByBoard(board).stream().map(c -> {
            BoardCommentDTO dto = new BoardCommentDTO();
            dto.setId(c.getId());
            dto.setContent(c.getContent());
            dto.setCreatedAt(c.getCreatedAt());
            dto.setBoardId(boardId);
            dto.setWriterId(c.getWriter().getId());
            dto.setWriterNickname(c.getWriter().getNickname());
            return dto;
        }).collect(Collectors.toList());
    }
}
