package Personal.project.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/addMember")
    public String addMember(){
        return "/members/addMemberForm";
    }

    @PostMapping("/addMember")
    public String hiMember(Member member){
        Member savedMember = memberRepository.addMember(member);
        return"/home";
    }


}
