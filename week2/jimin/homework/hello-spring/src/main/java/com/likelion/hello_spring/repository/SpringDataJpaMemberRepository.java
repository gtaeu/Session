package com.likelion.hello_spring.repository;

import com.likelion.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속받기 때문에 JpaRepository가 
// SpringDataJpaMemberRepository의 구현체를 자동으로 만들어서 스프링 빈에 등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    Optional<Member> findByName(String name);

}
