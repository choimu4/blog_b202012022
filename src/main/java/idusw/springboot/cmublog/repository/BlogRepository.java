package idusw.springboot.cmublog.repository;

import idusw.springboot.cmublog.entity.BlogEntity;
import idusw.springboot.cmublog.model.BlogDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    Optional<BlogEntity> findByIdx(BlogDto dto); // interface 상속,

}
