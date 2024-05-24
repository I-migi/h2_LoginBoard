package Personal.project.login;

import Personal.project.member.Member;
import Personal.project.member.MemberRepository;
import Personal.project.product.Product;
import Personal.project.product.ProductRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static java.rmi.server.LogStream.log;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;
    private final ProductRepository productRepository;

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "/home";
        }

        try {
            Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

            if (loginMember == null) {
                model.addAttribute("errorMessage","비밀번호가 틀립니다");
                return "/home";
            }

            List<Product> products = productRepository.findAll();
            model.addAttribute("products", products);

            session.setAttribute("member",loginMember);
            return "/products/products";
        } catch (Exception e) {

            model.addAttribute("errorMessage","아이디가 틀립니다");
            return "/home";
        }
    }
}


//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult) {
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//        if (loginMember == null) {
//            return "/home";
//        }
//        return "/products/products";
//    }
//}




