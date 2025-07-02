package com.fort4.cnc.domain.board;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<String> imagePathList;//이미지 업로드


}

