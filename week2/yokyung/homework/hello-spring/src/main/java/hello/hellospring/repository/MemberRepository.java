package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원을 저장하면 저장한 회원이 반환됨
    Optional<Member> findById(Long id); //아이디로 회원을 찾는것, findById, findByName 두 방식으로 member 검색 가
    Optional<Member> findByName(String name); //Optional은 null이 반환될 때 처리하는 방식 중 하나
    List<Member> findAll(); //지금까지 저장된 모든 회원들을 반환해줌
}
