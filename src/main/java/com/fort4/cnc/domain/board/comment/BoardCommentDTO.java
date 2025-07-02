package com.fort4.cnc.domain.board.comment;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardCommentDTO {
    private Long id;
    private Long boardId;
    private Long writerId;
    private String writerNickname;
    private String content;
    private LocalDateTime createdAt;
}

