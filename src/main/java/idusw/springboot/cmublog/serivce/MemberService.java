package idusw.springboot.cmublog.serivce;

import idusw.springboot.cmublog.entity.MemberEntity;
import idusw.springboot.cmublog.model.MemberDto;

import java.util.List;

public interface MemberService {
    int create(MemberDto memberDto);
    MemberDto readByIdx(Long idx);
    List<MemberDto> readAll();
    int update(MemberDto memberDto);
    int delete(MemberDto memberDto);

    // 검색 메서드 추가
    List<MemberDto> findByName(String name);
    List<MemberDto> findByEmail(String email);
    List<MemberDto> findByPhone(String phone);

    MemberDto loginById(MemberDto memberDto); // id / pw 활용

    // Conversion
    default MemberEntity dtoToEntity(MemberDto memberDto) {
        MemberEntity entity = MemberEntity.builder()
                .idx(memberDto.getIdx())
                .id(memberDto.getId())
                .pw(memberDto.getPw())
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .address(memberDto.getAddress())
                .build();
        return entity;
    }
    
    default MemberDto entityToDto(MemberEntity entity) {
        MemberDto memberDto = MemberDto.builder()
                .idx(entity.getIdx())
                .id(entity.getId())
                .pw(entity.getPw())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return memberDto;
    }


}
