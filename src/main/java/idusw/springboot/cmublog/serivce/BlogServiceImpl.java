package idusw.springboot.cmublog.service;

import idusw.springboot.cmublog.entity.BlogEntity;
import idusw.springboot.cmublog.entity.MemberEntity;
import idusw.springboot.cmublog.model.BlogDto;
import idusw.springboot.cmublog.repository.BlogRepository;
import idusw.springboot.cmublog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements idusw.springboot.cmublog.service.BlogService {
    final BlogRepository blogRepository;
    final MemberRepository memberRepository;

    public BlogServiceImpl(BlogRepository blogRepository, MemberRepository memberRepository) {
        this.blogRepository = blogRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public int create(BlogDto dto) {
        blogRepository.save(dtoToEntity(dto));
        return 0;
    }

    @Override
    @Transactional
    public BlogDto read(BlogDto dto) {
        Optional<BlogEntity> blogEntityOptional = blogRepository.findById(dto.getIdx());
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByIdx(blogEntityOptional.get().getBlogger().getIdx());
        return entityToDto(blogEntityOptional.get(), memberEntityOptional.get());
    }

    @Override
    public List<BlogDto> readList() {
        List<BlogEntity> blogEntityList = blogRepository.findAll();
        List<BlogDto> blogDtoList = new ArrayList<>();
        for (BlogEntity blogEntity : blogEntityList) {
            MemberEntity memberEntity = MemberEntity.builder()
                    .idx(blogEntity.getBlogger().getIdx())
                    .build();
            blogDtoList.add(entityToDto(blogEntity, memberEntity));
        }
        return blogDtoList;
    }

    @Override
    public int update(BlogDto dto) {
        blogRepository.save(dtoToEntity(dto));
        return 0;
    }

    @Override
    public int delete(BlogDto dto) {
        blogRepository.delete(dtoToEntity(dto));
        return 0;
    }
}
