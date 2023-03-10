package cookbook.cookbookrecipeapplication.controllers;

import cookbook.cookbookrecipeapplication.PropertiesReader;
import cookbook.cookbookrecipeapplication.models.Follower;
import cookbook.cookbookrecipeapplication.models.RecentActivity;
import cookbook.cookbookrecipeapplication.models.User;
import cookbook.cookbookrecipeapplication.services.ChapterDaoService;
import cookbook.cookbookrecipeapplication.services.RecipeDaoService;
import cookbook.cookbookrecipeapplication.services.UserDaoService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class UserController {
    private UserDaoService userDao;
    private RecipeDaoService recipeDao;
    private ChapterDaoService chapterDao;

    public UserController(UserDaoService userDao, RecipeDaoService recipeDao, ChapterDaoService chapterDao) {
        this.userDao = userDao;
        this.recipeDao = recipeDao;
        this.chapterDao = chapterDao;
    }

    //* LOG IN *//
    @GetMapping("/login")
    public String showLogIn() {
        return "/login";
    }

    @PostMapping("/login")
    public String loginUser(Model model) {
        model.addAttribute("user", new User());
        return "redirect:/feed";
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

    //* USER PROFILE *//
    @GetMapping("/profile/{username}")
    public String showProfile(@PathVariable String username, Model model) {
        User user = userDao.findUserByUsername(username);
        // Checks if user is logged in
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if (!name.equals("anonymousUser")){
            System.out.println("ISNT NULL " + (((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
            // Checks if user is on own page / if so, shows edit profile button
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String loggedInUserName = loggedInUser.getName();
            User loggedInUserObj = userDao.findUserByUsername(loggedInUserName);
            if (loggedInUserObj.getId() == user.getId()){
                model.addAttribute("showEditProfile", true);
                // Checks if logged in user is following page user
            }
            else if (userDao.isUserFollowingUser(user, ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()))){
                model.addAttribute("isFollowing", true);
            } else {
                model.addAttribute("isNotFollowing", true);
            }
        }
        model.addAttribute("filestack", PropertiesReader.getProperty("FILESTACK_API_KEY"));
        model.addAttribute("user", user);
        model.addAttribute("recipes", user.getCustom_recipes());
        model.addAttribute("savedRecipes", chapterDao.findSavedChapterByUser(user).getSavedRecipes());
        model.addAttribute("recentActivity", user.getRecentActivities());
        model.addAttribute("recipeDao", recipeDao);
        return "/profile";
    }

    @GetMapping("/profile")
    public String showUserProfileOrRedirect() {
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            return "redirect:/profile/" + ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/profile/edit/{username}")
    public String editUserProfile(
            Model model,
            @PathVariable String username,
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "imageURL") String imageURL,
            @RequestParam(name = "userBio") String userBio) {
        User user = userDao.findUserByUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserBio(userBio);

        if (!user.getProfilePicture().equals(imageURL)) {
            user.setProfilePicture(imageURL);
        }

        userDao.editUser(user);
            return "redirect:/profile/" + username;
    }

    //* ACTIVITY FEED *//
    @GetMapping("/feed")
    public String showActivityFeed(Model model) {
        if (((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFollowing() == null){
            model.addAttribute("noResults", true);
        } else {
            model.addAttribute("recentActivity", userDao.getAllFollowingRecentActivity(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())));
        }
        return "/feed";
    }

    // UPDATE USER

    // DELETE USER
    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userDao.deleteUser(id);
    }

    //* FOLLOW *//
    @GetMapping("/follow/{user_id}")
    public String followUser(@PathVariable long user_id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Follower follower = new Follower(loggedInUser, userDao.findUserById(user_id));
        userDao.followUser(follower);

        RecentActivity recentActivity = new RecentActivity(
                4,
                new Date(),
                loggedInUser,
                userDao.findUserById(user_id)
        );
        userDao.saveRecentActivity(recentActivity);
        return "redirect:/profile/" + userDao.findUserById(user_id).getUsername();
    }

    @GetMapping("/unfollow/{user_id}")
    public String unfollowUser(@PathVariable long user_id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDao.unfollowUser(userDao.findUserById(user_id), loggedInUser);
        return "redirect:/profile/" + userDao.findUserById(user_id).getUsername();
    }

}
