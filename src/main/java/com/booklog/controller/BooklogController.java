package com.booklog.controller;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.booklog.dto.BooklogDto;
import com.booklog.dto.BooklogFileDto;
import com.booklog.service.BooklogService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/booklog")
public class BooklogController {

    @Autowired
    private BooklogService booklogService;

    @GetMapping("/booklogList.do")
    public ModelAndView getBooklogList() throws Exception {
        ModelAndView mv = new ModelAndView("/booklog/booklogList");

        List<BooklogDto> list = booklogService.selectBooklogList();

        mv.addObject("list", list);

        return mv;
    }

    @GetMapping("/openBooklogWrite.do")
    public String writeBooklog() {
        return "/booklog/booklogWrite";
    }

    @PostMapping("/insertBooklog.do")
    public String insertBooklog(BooklogDto dto, MultipartHttpServletRequest request) throws Exception {
        booklogService.insertBooklog(dto, request);
        return "redirect:/booklog/booklogList.do";
    }

    @GetMapping("/booklogDetail.do")
    public ModelAndView getBooklogDetail(@RequestParam int bookId) throws Exception {
        ModelAndView mv = new ModelAndView("/booklog/booklogDetail");

        BooklogDto booklog = booklogService.selectBooklog(bookId);

        mv.addObject("booklog", booklog);

        return mv;
    }

    @PostMapping("/updateBooklog.do")
    public String updateBooklog(BooklogDto dto) throws Exception {
        booklogService.updateBooklog(dto);
        return "redirect:/booklog/booklogDetail.do?bookId=" + dto.getBookId();
    }

    @PostMapping("/deleteBooklog.do")
    public String deleteBooklog(BooklogDto dto) throws Exception {
        booklogService.deleteBooklog(dto.getBookId());
        return "redirect:/booklog/booklogList.do";
    }

    @GetMapping("/downloadBooklogFile.do")
    public void downloadBooklogFile(@RequestParam("idx") int idx, @RequestParam("bookId") int bookId,
            HttpServletResponse response) throws Exception {
        // idx와 bookId가 일치하는 파일 정보를 조회
        BooklogFileDto booklogFileDto = booklogService.selectBooklogFileInfo(idx, bookId);
        if (ObjectUtils.isEmpty(booklogFileDto)) {
            return;
        }

        // 원본 파일 저장 위치에서 파일을 읽어서 호출(요청)한 곳으로 파일을 응답으로 전달
        Path path = Paths.get(booklogFileDto.getStoredFilePath());
        byte[] file = Files.readAllBytes(path);

        response.setContentType("application/octet-stream");
        response.setContentLength(file.length);
        response.setHeader("Content-Disposition",
                "attachment; fileName=\"" + URLEncoder.encode(booklogFileDto.getOriginalFileName(), "UTF-8") + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.getOutputStream().write(file);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

}
