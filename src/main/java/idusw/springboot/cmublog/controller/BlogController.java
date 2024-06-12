package idusw.springboot.cmublog.controller;

import idusw.springboot.cmublog.model.BlogDto;
import idusw.springboot.cmublog.serivce.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("blogs/")
public class BlogController {
    final BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/delete/{idx}")
    public String deleteBlog(@PathVariable Long idx, Model model){
        // Controller가 Service에게 요청
        BlogDto dto = BlogDto.builder()
                .idx(idx)
                .build();
        blogService.delete(dto);
        // Controller가 View에게 처리 결과를 전달
        model.addAttribute("attr-name", "attr-value"); // delete에는 크게 필요 없음
        return "redirect:/";
    }
}
