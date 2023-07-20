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


            tx.commit(); // 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 꼭 닫아야 함
        }
        emf.close();

    }
}
