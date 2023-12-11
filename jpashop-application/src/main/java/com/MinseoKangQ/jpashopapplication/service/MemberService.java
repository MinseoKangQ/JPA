package com.MinseoKangQ.jpashopapplication.service;

import com.MinseoKangQ.jpashopapplication.domain.Member;
import com.MinseoKangQ.jpashopapplication.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) // 기본적으로 데이터의 변경은 트랜잭션 내에서 진행되어야 함
@RequiredArgsConstructor // final 필드로만 injection
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId(); // 항상 값이 있다는 보장이 있음
    }

    // 중복 회원 검증, 최후의 검증으로 DB에서 unique 제약 조건 권장
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 한 명 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
