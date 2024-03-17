package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    List<Member> findTop3HelloBy(); // 전체 조회

    @Query(name = "Member.findByUsername") // NamedQuery 간편하게 가져오기, -> 이 줄 주석해도 잘 돌아감
        // 1순위 : (Member + "." + 메서드명) - NamedQuery
        // 2순위 : 없으면 메소드명으로 쿼리 생성
    List<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username and m.age = :age") // 실무에서 많이 사용, 애플리케이션 실행 시점에 쿼리 검증
    List<Member> findUser(@Param("username") String username, @Param("age")int age);
}
