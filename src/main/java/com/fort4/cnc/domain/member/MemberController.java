package com.fort4.cnc.domain.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fort4.cnc.common.RenderController;
import com.fort4.cnc.domain.member.dto.LoginMemberDTO;
import com.fort4.cnc.domain.member.dto.MemberDTO;

@Controller
@RequestMapping("/member")
public class MemberController extends RenderController {
	
	@Autowired
	private MemberService mService;

    // 회원가입 폼
    @GetMapping("/signup")
    public String signupForm(Model model) {
        return render("member/signup", model);
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(MemberDTO dto, RedirectAttributes rAt, Model model) {
        boolean result = mService.register(dto);
        if (!result) {
        	model.addAttribute("errorMSG", "이미 존재하는 닉네임입니다.");
            return "member/signup";
        }
        rAt.addFlashAttribute("succesMSG", "회원가입 되었습니다! 로그인 해주세요.");
        return "redirect:/member/login";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm(Model model) 
    {
        return render("member/login", model);
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String nickname,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes rAt,
                        Model model) 
    {
        MemberEntity member = mService.login(nickname, password);
        if (member == null) {
        	model.addAttribute("errorMSG", "로그인 실패: 비밀번호가 틀렸습니다.");
            return "member/login";
        }
        
        LoginMemberDTO loginUser = new LoginMemberDTO(member);
        
        session.setAttribute("loginUser", loginUser);
        rAt.addFlashAttribute("successMSG", "로그인 성공!");
        return "redirect:/index";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes rAt) 
    {
        session.invalidate();
        rAt.addFlashAttribute("successMSG", "정상적으로 로그아웃 되었습니다.");
        return "redirect:/";
    }
}
