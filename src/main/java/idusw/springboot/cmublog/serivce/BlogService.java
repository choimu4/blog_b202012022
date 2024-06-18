package idusw.springboot.cmublog.service;

import idusw.springboot.cmublog.entity.BlogEntity;
import idusw.springboot.cmublog.entity.MemberEntity;
import idusw.springboot.cmublog.model.BlogDto;

import java.util.List;

public interface BlogService {
    int create(BlogDto dto);
    BlogDto read(BlogDto dto);
    List<BlogDto> readList();
    int update(BlogDto dto);
    int delete(BlogDto dto);

    default BlogEntity dtoToEntity(BlogDto dto) {
        MemberEntity member = MemberEntity.builder()
                .idx(dto.getWriterIdx())
                .build();
        BlogEntity entity = BlogEntity.builder()
                .idx(dto.getIdx())
                .title(dto.getTitle())
                .content(dto.getContent())
                .views(dto.getViews())
                .block(dto.getBlock())
                .blogger(member)
                .build();
        return entity;
    }

    default BlogDto entityToDto(BlogEntity entity, MemberEntity member) {
        BlogDto dto = BlogDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .views(entity.getViews())
                .content(entity.getContent())
                .block(entity.getBlock())
                .writerIdx(member.getIdx())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
