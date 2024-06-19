package idusw.springboot.cmublog.serivce;

import idusw.springboot.cmublog.entity.MemberEntity;
import idusw.springboot.cmublog.model.MemberDto;
import idusw.springboot.cmublog.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public int create(MemberDto memberDto) {
        MemberEntity entity = dtoToEntity(memberDto);
        memberRepository.save(entity);
        return 1;
    }

    @Override
    public MemberDto readByIdx(Long idx) {
        Optional<MemberEntity> entityOptional = memberRepository.findByIdx(idx);
        return entityOptional.map(this::entityToDto).orElse(null);
    }

    @Override
    public List<MemberDto> readAll() {
        List<MemberEntity> entities = memberRepository.findAll();
        List<MemberDto> dtos = new ArrayList<>();
        for (MemberEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }

    @Override
    public int update(MemberDto memberDto) {
        Optional<MemberEntity> entityOptional = memberRepository.findById(memberDto.getIdx());
        if (entityOptional.isPresent()) {
            MemberEntity entity = entityOptional.get();
            entity.setEmail(memberDto.getEmail());
            entity.setName(memberDto.getName());
            entity.setPw(memberDto.getPw());
            entity.setPhone(memberDto.getPhone());
            entity.setAddress(memberDto.getAddress());
            memberRepository.save(entity);
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(MemberDto memberDto) {
        Optional<MemberEntity> entityOptional = memberRepository.findById(memberDto.getIdx());
        if (entityOptional.isPresent()) {
            memberRepository.delete(entityOptional.get());
            return 1;
        }
        return 0;
    }

    @Override
    public MemberDto loginById(MemberDto memberDto) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByIdAndPw(memberDto.getId(), memberDto.getPw());
        return memberEntityOptional.map(this::entityToDto).orElse(null);
    }

    // 검색 메서드 구현
    @Override
    public List<MemberDto> findByName(String name) {
        List<MemberEntity> entities = memberRepository.findByName(name);
        List<MemberDto> dtos = new ArrayList<>();
        for (MemberEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }

    @Override
    public List<MemberDto> findByEmail(String email) {
        List<MemberEntity> entities = memberRepository.findByEmail(email);
        List<MemberDto> dtos = new ArrayList<>();
        for (MemberEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }

    @Override
    public List<MemberDto> findByPhone(String phone) {
        List<MemberEntity> entities = memberRepository.findByPhone(phone);
        List<MemberDto> dtos = new ArrayList<>();
        for (MemberEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }
}
