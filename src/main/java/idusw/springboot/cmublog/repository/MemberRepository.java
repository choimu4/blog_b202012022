package idusw.springboot.cmublog.repository;

import idusw.springboot.cmublog.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends
        JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByIdx(Long idx);
    Optional<MemberEntity> findByIdAndPw(String id, String pw);

    List<MemberEntity> findByName(String name);
    List<MemberEntity> findByEmail(String email);
    List<MemberEntity> findByPhone(String phone);

    //QuerydslPredicateExecutor<MemberEntity> {
    /*
    @Query("select m from MemberEntity m where m.id = :id and m.pw = :pw")
    Object getMemberEntityById(@Param("id") String id, @Param("pw") String pw);

     */

}
