package com.MinseoKangQ.jpashopapplication.repository;

import com.MinseoKangQ.jpashopapplication.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); // 타입, 기본키
    }

    public List<Member> findAll() { // SQL은 테이블 대상으로 쿼리하지만, JPQL은 엔티티 객체에 대해 쿼리함
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
