package com.likelion.hello_spring.repository;

import com.likelion.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // Jpa는 EntityManager로 모든 것이 동작 (build.gradle에 추가하면 알아서 생성됨)
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // creteQuery : PK 기반으로 객체를 찾지 않을 때 사용하는 jpql 객체지향 쿼리 언어를 위한 것
    // jpql : 쿼리를 날리는 대상이 테이블 X, 객체 O

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
