package com.fort4.cnc.domain.board.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fort4.cnc.domain.board.BoardEntity;

public interface BoardCommentRepo extends JpaRepository<BoardCommentEntity, Long> {
	
    List<BoardCommentEntity> findByBoard(BoardEntity board);
    
}

