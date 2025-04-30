package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean //스프링 빈을 내가 등록할거야.
    public MemberService memberService(){
        return new MemberService(memberRepository);//멤버 서비스가 스프링 빈에 등록됨
    }

    //@Bean
    //public MemberRepository memberRepository() {
        // return new MemoryMemberRepository(); //구현체
        //return new JdbcMemberRepository(dataSource); //JDBC
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    //}
}
