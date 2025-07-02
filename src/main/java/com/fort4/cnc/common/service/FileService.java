package com.fort4.cnc.common.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final String uploadDir = "C:/upload"; // 실제 서버 경로

    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;

        String originalName = file.getOriginalFilename();
        String saveName = UUID.randomUUID() + "_" + originalName;
        File destination = new File(uploadDir, saveName);

        // 디렉토리 없으면 생성
        if (!destination.getParentFile().exists()) {
            destination.getParentFile().mkdirs();
        }

        file.transferTo(destination);
        return saveName;
    }
}

