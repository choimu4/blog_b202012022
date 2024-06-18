package idusw.springboot.cmublog.model;


import idusw.springboot.cmublog.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

// boilerplate code : 상용구 코드, 뻔한 코드
// Annotation : 컴파일러에게 처리를 요청하는 부연 설명 vs. Comment : 주석 - 소스코드 설명

@Data // @Getter @Setter  @EqualsAndHashCode @RequiredArgsConstructor
// @NoArgsConstructor
// @AllArgsConstructor
@Builder  // Design Pattern 중 하나로 생성시 명확성을 제공함

/**
 * Define a Member Class
 * @Author egyou
 */
public class MemberDto {
    private Long idx;
    private String id;
    private String email;
    private String name;
    private String pw;
    private String phone;
    private String address;
    private LocalDateTime regDate;
    private LocalDateTime modDate;



}
