package Personal.project.product;

import Personal.project.member.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor

public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/products")
    public String Products(Model model, HttpSession session){
        Member loggedUser =(Member) session.getAttribute("member");
        if(loggedUser != null) {
            model.addAttribute("loggedUser",loggedUser);
        }
        List<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "/products/products";
    }


    @GetMapping("/addProduct")
    public String addProductForm(Model model,HttpSession session){
        Member loggedUser =(Member) session.getAttribute("member");
        if(loggedUser != null) {
            model.addAttribute("loggedUser",loggedUser);
        }


        return "/products/addProductForm";
    }
    @PostMapping("/addProduct")
    public String addProduct(Product product, Model model,HttpSession session){
        Member loggedUser =(Member) session.getAttribute("member");
        if(loggedUser != null) {
            model.addAttribute("loggedUser",loggedUser);
        }
        Product savedProduct = productRepository.addProduct(product);
        List<Product> products = productRepository.findAll();
        model.addAttribute("products",products);

        return "/products/products";
    }

    @GetMapping("/{productId}/edit")
    public String editForm(@PathVariable("productId") long productId, Model model,HttpSession session){
        Member loggedUser =(Member) session.getAttribute("member");
        if(loggedUser != null) {
            model.addAttribute("loggedUser",loggedUser);
        }
        Product product = productRepository.findById(productId);
        model.addAttribute("product",product);
        return "/products/editProductForm";
    }

    @PostMapping("/{productId}/edit")
    public String editProduct(@PathVariable("productId") long productId,
                              @RequestParam("name") String name,
                              @RequestParam("price") int price
            ,Model model, HttpSession session
    ){
        Member loggedUser =(Member) session.getAttribute("member");
        if(loggedUser != null) {
            model.addAttribute("loggedUser",loggedUser);
        }
        Product productToUpdate = productRepository.findById(productId);
        productToUpdate.setName(name);
        productToUpdate.setPrice(price);
        productRepository.updateProduct(productId,productToUpdate);
        List<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "redirect:/products";
    }
    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") long productId, HttpSession session,Model model
    ){
        Member loggedUser =(Member) session.getAttribute("member");
        if(loggedUser != null) {
            model.addAttribute("loggedUser",loggedUser);
        }
        productRepository.deleteProduct(productId);
        return "redirect:/products";
    }

}