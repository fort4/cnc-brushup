package com.fort4.cnc.domain.board;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private Long writerId;
    private String writerNickname;
    private LocalDateTime createdAt;

}

