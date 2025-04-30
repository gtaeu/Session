package com.likelion.hello_spring.repository;

import com.likelion.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 우선 회원들을 저장해 둘 메모리 기반 내부 데이터베이스
    private static Map<Long, Member> store = new HashMap<>();
    // id로 사용할 일련번호
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {

        // 넣어준 파라미터와 같은 value가 있다면 해당 객체를 반환해줌
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // 리스트가 사용하기 편리하므로 Map -> List로 변환해서 반환
        // 리스트는 values로 열거, 즉 Member 객체들로 구성
        return new ArrayList<>(store.values());
    }

    public void clearStore()
    {
        store.clear();
    }
}
