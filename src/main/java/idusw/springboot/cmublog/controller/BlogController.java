package idusw.springboot.cmublog.controller;

import idusw.springboot.cmublog.model.BlogDto;
import idusw.springboot.cmublog.model.MemberDto;
import idusw.springboot.cmublog.service.BlogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blogs/")
public class BlogController {
    final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/delete/{idx}")
    public String deleteBlog(@PathVariable Long idx, Model model, HttpSession session){
        BlogDto dto = BlogDto.builder()
                .idx(idx)
                .build();
        blogService.delete(dto);
        session.invalidate();
        model.addAttribute("attr-name", "attr-value"); // delete에는 크게 필요 없음
        return "redirect:/blogs/";
    }

    @GetMapping("/reg-form")
    public String getCreateForm(Model model, HttpSession session) {
        Long writerIdx = (Long) session.getAttribute("idx");
        model.addAttribute("blogDto", BlogDto.builder().writerIdx(writerIdx).build());
        return "./blogs/post";
    }

    @PostMapping("/post")
    public String createBlog(@ModelAttribute BlogDto blogDto) {
        blogService.create(blogDto);
        return "redirect:/blogs/";
    }

    @GetMapping("/{idx}")
    public String getBlogDetail(@PathVariable("idx") Long idx, Model model) {
        BlogDto dto = BlogDto.builder()
                .idx(idx)
                .build();
        model.addAttribute("blog", blogService.read(dto));
        return "./blogs/detail";
    }

    @GetMapping("")
    public String getBlogList(Model model) {
        model.addAttribute("blogs", blogService.readList());
        return "./blogs/list";
    }

    @GetMapping("/edit/{idx}")
    public String editBlogForm(@PathVariable("idx") Long idx, Model model) {
        BlogDto dto = blogService.read(BlogDto.builder().idx(idx).build());
        model.addAttribute("blogDto", dto);
        return "./blogs/edit";
    }

    @PostMapping("/edit")
    public String updateBlog(@ModelAttribute BlogDto blogDto) {
        blogService.update(blogDto);
        return "redirect:/blogs/";
    }
}
