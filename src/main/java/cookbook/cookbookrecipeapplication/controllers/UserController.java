package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import cookbook.cookbookrecipeapplication.services.UserDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserDaoService userDao;
    private RecipeDaoService recipeDao;

    public UserController(UserDaoService userDao, RecipeDaoService recipeDao) {
        this.userDao = userDao;
        this.recipeDao = recipeDao;
    }

    //* LOG IN *//
    @GetMapping("/login")
    public String showLogIn() {
        return "/login";
    }

    @PostMapping ("/login")
    public String loginUser(Model model) {
        model.addAttribute("user", new User());
        return "redirect:/profile";
    }

    //* REGISTRATION *//
    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute User user) {
        userDao.registerUser(user);
        return "redirect:/login";
    }

}