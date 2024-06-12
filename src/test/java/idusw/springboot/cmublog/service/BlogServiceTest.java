package idusw.springboot.cmublog.service;

import idusw.springboot.cmublog.entity.BlogEntity;
import idusw.springboot.cmublog.entity.MemberEntity;
import idusw.springboot.cmublog.model.BlogDto;
import idusw.springboot.cmublog.model.MemberDto;
import idusw.springboot.cmublog.repository.BlogRepository;
import idusw.springboot.cmublog.serivce.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BlogServiceTest {
    @Autowired
    BlogService blogService;

    @Test
    public void registerBlog() {
        BlogDto dto = BlogDto.builder()
                .title("제목5")
                .content("더워")
                .writerIdx(3L)
                .block("non")
                .build();
        blogService.create(dto);
    }
    @Test
    public void getBlogs() {
        List<BlogDto> blogDtoList = blogService.readList();

        for(BlogDto dto : blogDtoList)
            System.out.println(dto.toString());
    }

    @Test
    public void getBlog() {
        BlogDto dto = BlogDto.builder()
                .idx((long) 5)
                .build();
        BlogDto blogdto = blogService.read(dto);
        System.out.println(blogdto.toString());
    }

    @Test
    public void deleteBlog(){
        BlogDto dto = BlogDto.builder()
                .idx((long)4)
                .build();
        blogService.delete(dto);
    }
    BlogEntity dtoToEntity(BlogDto dto) {
        MemberEntity member = MemberEntity.builder()
                .idx(dto.getWriterIdx())
                .build();
        BlogEntity entity = BlogEntity.builder()
                .idx(dto.getIdx())
                .title(dto.getTitle())
                .content(dto.getContent())
                .block(dto.getBlock())
                .views(dto.getViews())
                .blogger(member)
                .build();
        return entity;
    }
    // MemberEntity -> : Controller에서는 Member를 다룸
    BlogDto entityToDto(BlogEntity entity, MemberEntity member) {
        BlogDto dto = BlogDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .views(entity.getViews())
                .content(entity.getContent())
                .writerIdx(member.getIdx())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .regDate((entity.getRegDate()))
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
