package com.MinseoKangQ.jpashopapplication.service;

import static org.junit.Assert.*;

import com.MinseoKangQ.jpashopapplication.domain.Member;
import com.MinseoKangQ.jpashopapplication.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class) // Spring 과 엮어서 테스트 하겠다
@SpringBootTest // SpringBoot 를 띄운 상태에서 실행
@Transactional // 기본적으로 테스트 후 Rollback 함
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {

        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {

        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        memberService.join(member2); // 예외 발생

        // then
        fail("예외가 발생해야 한다.");
    }

}