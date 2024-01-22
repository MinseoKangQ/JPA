package com.fastcampus.jpa.bookmanager.repository;

import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;
import com.fastcampus.jpa.bookmanager.domain.User;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    DataSource dataSource;

    @BeforeAll
    public void init() {
        try (Connection conn = dataSource.getConnection()) {
            // 자신의 script path 넣어주면 됨
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("/db/h2/data.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void crud() { // create, read, update, delete
        // 정렬
        // List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));

        // 아이디가 1L, 3L, 5L 인 유저 찾기
        // List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));

        // System.out.println("======");
        // users.forEach(System.out::println);
        // System.out.println("======");

        // Optional<User> user = userRepository.findById(1L);
        // System.out.println(user);

        // User user = userRepository.findById(1L).orElse(null);
        // System.out.println("=====");
        // System.out.println(user);
        // System.out.println("=====");

        // 하위 1과 2는 동일하다. flush 는 로직에 영향을 주지는 않고 DB 반영 시점을 결정한다.

        // 1
        // userRepository.save(new User("mew martin", "newmartin@hello.com"));
        // userRepository.flush();

        // 2
        // userRepository.saveAndFlush(new User("new martin",  "newmartin@hello.com"));
        // userRepository.findAll().forEach(System.out::println);

        // 컬럼 개수
        // long count = userRepository.count();
        // System.out.println(count);

        // existsById는 count 쿼리를 이용함
        // boolean exists = userRepository.existsById(1L);
        // System.out.println(exists);

        // id로 찾아서 삭제
        // userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));

        // userRepository.deleteById(1L);
        // userRepository.findAll().forEach(System.out::println);

        // userRepository.deleteAll();

        // 1번이 존재하는지, 3번이 존재하는지 쿼리 (select), 각각 삭제 쿼리 (delete) -> 성능 이슈
        // userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L)));

        // select 쿼리가 나가지 않고 무조건 전체 삭제
        // userRepository.deleteAllInBatch();

        // userRepository.findAll().forEach(System.out::println);


        // === 페이징 ===
        Page<User> users = userRepository.findAll(PageRequest.of(1, 3));

        // System.out.println("page: " + users);
        // System.out.println("totalElements: " + users.getTotalElements());
        // System.out.println("totalPages: " + users.getTotalPages());
        // System.out.println("numberOfElements: " + users.getNumberOfElements());
        // System.out.println("sort: " + users.getSort());
        // System.out.println("size: " + users.getSize());

        // users.getContent().forEach(System.out::println);

        // Query By Example - 실제로 많이 쓰지는 않음
        // fastcampus.com 이라는 이메일로 끝나는 것 찾기
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());

        Example<User> example = Example.of(new User("ma", "fastcampus.com"), matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }

}