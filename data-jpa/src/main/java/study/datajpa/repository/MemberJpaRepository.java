package study.datajpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    // 저장
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    // 삭제
    public void delete(Member member) {
        em.remove(member);
    }

    // 전체 조회
    public List<Member> findAll() {
        // JPQL 사용
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // Optional 로 조회
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // 개수
    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    // 단건 조회
    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    // 회원과 나이로 조회
    public List<Member> findByUsernameAndAgeGreaterThen(String username, int age) {
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age")
                .setParameter("username" , username)
                .setParameter("age", age)
                .getResultList();
    }

    // NameQuery 사용 -> 실무에서는 거의 사용 X
    public List<Member> findByUsername(String username) {
        return em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    /**
     * 순수 JPA 페이징
     * offset : 몇 번째부터 시작
     * limit : 몇 번째까지 가져올지
     */
    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Member m where m.age = :age order by m.username desc")
                .setParameter("age", age)
                .setFirstResult(offset) // 어디서부터 가져올 것인지
                .setMaxResults(limit) // 몇 개 가져올지
                .getResultList();
    }

    /**
     * 순수 JPA 페이징
     * totalCount 에는 sort 필요 없음 -> 성능 최적화
     */
    public long totalCount(int age) {
        return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }
}
