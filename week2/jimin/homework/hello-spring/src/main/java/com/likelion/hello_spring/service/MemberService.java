package com.likelion.hello_spring.service;

// service -> 비지니스에 가까운 기능들을 구현하는 패키지

import com.likelion.hello_spring.domain.Member;
import com.likelion.hello_spring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/// 비지니스 서비스
/// 1. 회원가입
/// 2. 회원 조회
///     2-1. 전체
///     2-2. 개별

//@Service
@Transactional  // JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 함 (join 메소드에서 데이터 변경 O)
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 1. id를 반환하는 회원가입 기능
    public Long join(Member member)
    {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member)
    {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 2. 회원 조회
    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }


}
