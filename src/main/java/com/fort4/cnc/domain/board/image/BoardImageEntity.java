package com.fort4.cnc.domain.board.image;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fort4.cnc.domain.board.BoardEntity;

import lombok.Getter;
import lombok.Setter;

/** 게시글의 이미지 첨부를 위한 테이블 */
@Entity
@Table(name = "board_image")
@Getter @Setter
public class BoardImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bimg_id")
    private Long id;
    
    // 일대다(매니투원) 기본 fetch전략은 eager이라 lazy로 바꿔줌
    // -> 순환 참조 방지, 필요할때만 로딩, 성능최적화(실제 사용x인 연관 엔티티 불러오지 않게)
    // 실무에선 manytoone을 lazy로 명시하는게 표준이라함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;
}
