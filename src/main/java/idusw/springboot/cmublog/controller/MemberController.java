package idusw.springboot.cmublog.controller;

import idusw.springboot.cmublog.model.MemberDto;
import idusw.springboot.cmublog.serivce.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("members/")
public class MemberController {

    final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("{idx}")
    public String getMemberById(@PathVariable("idx") Long idx, Model model) {
        MemberDto dto = memberService.readByIdx(idx);
        model.addAttribute("dto", dto);
        return "./members/profile";
    }

    @GetMapping("")
    public String getMembers(Model model) {
        List<MemberDto> dtoList = memberService.readAll();
        model.addAttribute("dtoList", dtoList);
        return "./members/list";
    }

    @GetMapping("login")
    public String getLogin(Model model) {
        model.addAttribute("memberDto", MemberDto.builder().build());
        return "./main/login";
    }

    @PostMapping("login")
    public String postLogin(@ModelAttribute("memberDto") MemberDto memberDto, Model model, HttpSession session) {
        String id = memberDto.getId();
        String pw = memberDto.getPw();

        MemberDto m = MemberDto.builder()
                .id(id)
                .pw(pw)
                .build();
        String msg = "";

        MemberDto ret = memberService.loginById(m);
        if (ret != null) {
            session.setAttribute("id", id);
            session.setAttribute("idx", ret.getIdx());
            msg = "로그인 성공";
        } else {
            msg = "로그인 실패";
        }
        model.addAttribute("message", msg);
        return "./errors/error-message";
    }

    @GetMapping("register")
    public String getRegister(Model model) {
        model.addAttribute("memberDto", MemberDto.builder().build());
        return "./main/register";
    }

    @PostMapping("register")
    public String postRegister(@ModelAttribute("memberDto") MemberDto memberDto, Model model) {
        if (memberService.create(memberDto) > 0)
            return "redirect:/";
        else
            return "./errors/error-message";
    }

    @GetMapping("/edit/{idx}")
    public String editMemberForm(@PathVariable("idx") Long idx, Model model) {
        MemberDto dto = memberService.readByIdx(idx);
        model.addAttribute("memberDto", dto);
        return "./members/edit";
    }

    @PostMapping("/edit/{idx}")
    public String updateMember(@PathVariable("idx") Long idx, @ModelAttribute("memberDto") MemberDto memberDto) {
        memberDto.setIdx(idx);
        memberService.update(memberDto);
        return "redirect:/";
    }

    @GetMapping("/delete/{idx}")
    public String deleteMember(@PathVariable("idx") Long idx, HttpSession session) {
        MemberDto memberDto = memberService.readByIdx(idx);
        if (memberDto != null) {
            memberService.delete(memberDto);
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("search")
    public String searchMembers(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "email", required = false) String email,
                                @RequestParam(value = "phone", required = false) String phone,
                                Model model) {
        List<MemberDto> dtoList;
        if (name != null && !name.isEmpty()) {
            dtoList = memberService.findByName(name);
        } else if (email != null && !email.isEmpty()) {
            dtoList = memberService.findByEmail(email);
        } else if (phone != null && !phone.isEmpty()) {
            dtoList = memberService.findByPhone(phone);
        } else {
            dtoList = memberService.readAll();
        }
        model.addAttribute("dtoList", dtoList);
        return "./members/list";
    }
}

