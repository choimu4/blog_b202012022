package idusw.springboot.cmublog.serivce;

import idusw.springboot.cmublog.entity.BlogEntity;
import idusw.springboot.cmublog.entity.MemberEntity;
import idusw.springboot.cmublog.model.BlogDto;
import idusw.springboot.cmublog.model.MemberDto;
import idusw.springboot.cmublog.repository.BlogRepository;
import idusw.springboot.cmublog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
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
    public BlogDto read(BlogDto dto) { // BlogDto = BlogEntity + MemberEntity
        Optional<BlogEntity> blogEntityOptional = blogRepository.findById(dto.getIdx());
        // Optional<BlogEntity> entity = blogRepository.findByIdx(dto);
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByIdx(blogEntityOptional.get().getBlogger().getIdx());
        return entityToDto(blogEntityOptional.get(), memberEntityOptional.get());
    }

    @Override
    public List<BlogDto> readList() {
        List<BlogEntity> blogEntityList = blogRepository.findAll(); // select * from blog;
        List<BlogDto> blogDtoList = new ArrayList<>();
        for(BlogEntity blogEntity : blogEntityList) {
            MemberEntity memberEntity = MemberEntity.builder()
                    .idx(blogEntity.getBlogger().getIdx())
                    .build();

            blogDtoList.add(entityToDto(blogEntity, memberEntity));
        }
        return blogDtoList;
    }


    @Override
    public int update(BlogDto dto) {
        return 0;
    }

    @Override
    public int delete(BlogDto dto) {
        return 0;
    }
}
