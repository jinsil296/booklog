package com.booklog.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.booklog.dto.BooklogFileDto;

@Component
public class FileUtils {
    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    // 요청을 통해서 전달받은 파일을 저장하고, 파일 정보를 반환하는 메서드
    public List<BooklogFileDto> parseFileInfo(int bookId, MultipartHttpServletRequest request) throws Exception {
        if (ObjectUtils.isEmpty(request)) {
            return null;
        }

        List<BooklogFileDto> fileInfoList = new ArrayList<>();

        // 파일을 저장할 디렉터리를 설정
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime now = ZonedDateTime.now();
        // String storedDir = uploadDir + "images\\" + now.format(dtf);
        String storedDir = uploadDir + now.format(dtf);
        File fileDir = new File(storedDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        // 업로드 파일 데이터를 디렉터리에 저장하고 정보를 리스트에 저장
        Iterator<String> fileTagNames = request.getFileNames();
        while (fileTagNames.hasNext()) {
            String fileTagName = fileTagNames.next();
            List<MultipartFile> files = request.getFiles(fileTagName);
            for (MultipartFile file : files) {
                String originalFileExtension = "";

                // 파일 확장자를 ContentType에 맞춰서 지정
                if (!file.isEmpty()) {
                    String contentType = file.getContentType();
                    if (ObjectUtils.isEmpty(contentType)) {
                        break;
                    } else {
                        if (contentType.contains("image/jpeg")) {
                            originalFileExtension = ".jpg";
                        } else if (contentType.contains("image/png")) {
                            originalFileExtension = ".png";
                        } else if (contentType.contains("image/gif")) {
                            originalFileExtension = ".gif";
                        } else {
                            break;
                        }
                    }

                    // 저장에 사용할 파일 이름을 조합
                    String storedFileName = Long.toString(System.nanoTime()) + originalFileExtension;
                    String storedFilePath = storedDir + "/" + storedFileName;
                    String webFilePath = "/uploads/" + now.format(dtf) + "/" + storedFileName; // 웹에서 접근 가능한 경로

                    // 파일 정보를 리스트에 저장
                    BooklogFileDto dto = new BooklogFileDto();
                    dto.setBookId(bookId);
                    dto.setFileSize(Long.toString(file.getSize()));
                    dto.setOriginalFileName(file.getOriginalFilename());
                    dto.setStoredFilePath(webFilePath); // 웹 경로로 변경
                    fileInfoList.add(dto);

                    // 파일 저장
                    fileDir = new File(storedFilePath);
                    file.transferTo(fileDir);
                }
            }
        }

        return fileInfoList;
    }
}
