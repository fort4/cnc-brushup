package com.fort4.cnc.domain.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fort4.cnc.common.RenderController;
import com.fort4.cnc.domain.member.dto.LoginMemberDTO;

@Controller
@RequestMapping("/board")
public class BoardController extends RenderController {

    @Autowired
    private BoardService boardService;

    // 글쓰기 폼
    @GetMapping("/write")
    public String writeGET(Model model) {
        return render("board/write", model);
    }

    // 글 작성 처리
    @PostMapping("/write")
    public String writePost(@ModelAttribute BoardDTO dto,
                            HttpSession session,
                            RedirectAttributes rAt) {

        // 로그인 유저 확인
        LoginMemberDTO loginUser = (LoginMemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            rAt.addFlashAttribute("error", "로그인 후 작성할 수 있습니다.");
            return "redirect:/member/login";
        }

        dto.setWriterId(loginUser.getId()); // 세션에서 작성자 ID 설정

        boardService.save(dto);
        return "redirect:/board/list";
    }
    
    // 게시글 목록
    @GetMapping("list")
    public String listGET(Model model)
    {
    	List<BoardDTO> list = boardService.listAll();
    	model.addAttribute("boardList", list);
    	
    	return render("board/list", model);
    }
    
    // 해당 게시글 상세
    @GetMapping("/detail/{id}")
    public String detailGET(@PathVariable Long id, Model model)
    {
    	BoardDTO post = boardService.detailById(id);
    	model.addAttribute("post", post);
    	
    	return render("board/detail", model);
    }
    
    // 게시글 삭제
    @GetMapping("/delete/{id}")
    public String deleteGET(@PathVariable Long id, RedirectAttributes rAt)
    {
    	boardService.deleteById(id);
    	rAt.addFlashAttribute("successMSG", "게시글이 삭제되었습니다.");
    	
    	return "redirect:/board/list";
    }
    
}
