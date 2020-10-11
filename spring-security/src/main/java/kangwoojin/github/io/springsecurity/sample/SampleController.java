package kangwoojin.github.io.springsecurity.sample;

import java.security.Principal;
import java.util.concurrent.Callable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kangwoojin.github.io.springsecurity.user.Account;
import kangwoojin.github.io.springsecurity.user.UserAccount;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SampleController {
    private final SampleService sampleService;

    public static void print(String current) {
        System.out.println(current);
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication : " + authentication);
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") Account account) {
        if (account != null) {
            model.addAttribute("message", "Hello Spring " + account.getUsername());
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
        sampleService.dashboard();
        model.addAttribute("message", "Hello " + principal.getName());
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello admin " + principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("message", "Hello user " + principal.getName());
        return "user";
    }

    @GetMapping("/async")
    @ResponseBody
    public Callable<String> async() {
        print("async");

        sampleService.asyncMethod();

        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                print("callable");
                return "async";
            }
        };
    }
}
