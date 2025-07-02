package com.fort4.cnc.domain.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepo extends JpaRepository<BoardEntity, Long>{
	
	// n+1 문제 제거
	@Query("SELECT b FROM BoardEntity b JOIN FETCH b.writer ORDER BY b.id DESC")
	List<BoardEntity> findAllWithWriter();

}
