package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

        User user = userRepository.findById(1L).orElse(null);
        System.out.println("=====");
        System.out.println(user);
        System.out.println("=====");

    }

}