package kangwoojin.github.io.springsecurity;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("message", "Hello Spring " + principal.getName());
        } else {
            model.addAttribute("message", "Hello Spring Security");
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Hello Info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello " + principal.getName());
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello admin " + principal.getName());
        return "admin";
    }
}
