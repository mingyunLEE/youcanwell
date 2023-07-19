package nom.youcanwell.course.member.repository;

import nom.youcanwell.course.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByMemberName(String memberName);
    Optional<Member> findByMemberEmail(String memberEmail);
}
