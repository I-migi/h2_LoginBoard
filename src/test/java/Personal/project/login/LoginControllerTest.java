package Personal.project.login;

import Personal.project.member.Member;
import Personal.project.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginControllerTest {
    private final MemberRepository memberRepository;
    private final LoginService loginService;

    LoginControllerTest(MemberRepository memberRepository, LoginService loginService) {
        this.memberRepository = memberRepository;
        this.loginService = loginService;
    }

    @Test
    public void login(){
        memberRepository.addMember(new Member(7L,"aa","aa","aa"));
        Member test = loginService.login("ss", "qq");
        assertThat(test).isEqualTo(null);
    }
}