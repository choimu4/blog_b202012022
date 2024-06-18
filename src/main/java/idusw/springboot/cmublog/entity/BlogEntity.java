package idusw.springboot.cmublog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "blogger")
public class BlogEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String content;

    @Column
    private Long views;

    @Column(length = 20)
    private String block;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity blogger;
}
