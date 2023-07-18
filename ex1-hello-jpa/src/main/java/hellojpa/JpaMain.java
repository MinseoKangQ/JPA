package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 받기
        tx.begin(); // 트랜잭션 시작

        // code
        try {
            // 생성
            /* Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member); // 저장 */

            // 조회
            /* Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName()); */

            // 삭제
            /* Member findMember = em.find(Member.class, 1L);
            em.remove(findMember); */

            // 수정
          /* Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA"); */

            // 전체 회원 조회
            /* List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            } */


            /*
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속
            System.out.println(" --- before --- ");
            em.persist(member);
            System.out.println(" --- after --- ");

            // select 쿼리가 안날아감, 1차 캐시에 저장된 것임
            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
             */

            // findMember1 에서만 쿼리 날림, findMember2는 1차 캐시에서 가져옴
           /*  Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L); */

            // 쿼리문은 commit 시점에 날아가는 것을 확인
            /* Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("-----");
            */

            // 수정 시 persist 작성 안해도 변경사항 저장됨
            /* Member member = em.find(Member.class, 150L);
            member.setName("ZZZZ"); */

            // flush 예제
            /* Member member = new Member(201L, "member201");
            em.persist(member);

            em.flush(); // 선을 기준으로 전에 insert 쿼리가 호출됨
            System.out.println("------"); */

            // 준영속 상태 예제
            Member member = em.find(Member.class, 150L);
            member.setName("AAAA");

            // em.detach(member); // JPA에서 관리하지 않도록 함, update 쿼리는 안나감 detach 했기 때문임
            em.clear(); // 통으로 1차 캐시 초기화
            Member member2 = em.find(Member.class, 150L); // 쿼리 위에서 한 번, 여기서 한 번 나옴 (1차 캐시가 clear로 지워졌기 때문)

            tx.commit(); // 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 꼭 닫아야 함
        }
        emf.close();

    }
}
