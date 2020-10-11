package kangwoojin.github.io.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kangwoojin.github.io.springsecurity.user.Account;
import kangwoojin.github.io.springsecurity.user.AccountService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/signup")
@Controller
public class SignUpController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public String signupForm(Model model) {
        model.addAttribute("account", new Account());
        return "signup";
    }

    @PostMapping
    public String processSignUp(@ModelAttribute Account account) {
        account.setRole("USER");
        accountService.createNew(account);
        return "redirect:/";
    }
}
