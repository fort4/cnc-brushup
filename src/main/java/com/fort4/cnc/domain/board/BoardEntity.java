package com.fort4.cnc.domain.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fort4.cnc.domain.board.comment.BoardCommentEntity;
import com.fort4.cnc.domain.board.image.BoardImageEntity;
import com.fort4.cnc.domain.member.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "board")
@Getter @Setter
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;
    
    //@Column(name = "writer_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id") //fk 컬럼 조인
    private MemberEntity writer; // users 테이블의 FK

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 게시글과 이미지: 일대다
    // mappedBy == fk를 가진 주인쪽을 의미
    // cascade = CascadeType.ALL == 게시글 저장 시 이미지들도 함께 저장됨 (전파)
    // new ArrayList<>() == NullPointerException 방지: 비어 있어도 항상 리스트로 초기화 (컬렉션이 비어있더라도 null이 아니게 함)
    // ArrayList는 비어있지만 존재하는 객체.
    // list, set같은 컬렉션 필드는 항상 초기화 하는 습관을 가져야겠다..
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardImageEntity> images = new ArrayList<>();

    // 게시글과 댓글: 일대다
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardCommentEntity> comments = new ArrayList<>();
}

