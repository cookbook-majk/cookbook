package cookbook.cookbookrecipeapplication;

import cookbook.cookbookrecipeapplication.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/feed") // users will be directed to their activity feed
                .permitAll() // anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login") // logged-out users will be sent to the home page
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeHttpRequests()
                .requestMatchers( // anyone can see these pages
                        "/", "/home", "/about", "/browse", "/profile/{username}", "/recipe/{id}", "/recipe/sp/{id}", "/search", "/search**", "/register", "/css/**", "/images/**", "/js/**", "/static/**", "/profile", "/logout", "/category/{categoryName}", "/error")
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeHttpRequests()
                .requestMatchers( // only authenticated users can visit these pages
                        "/profile/{username}/edit", "/logout", "/feed", "/create", "/recipe/{id}/edit", "/recipe/{id}/delete", "/follow/{user_id}", "/unfollow/{user_id}", "/chapter/save/{id}", "/chapter/unsave/{id}", "/chapter/save/sp/{id}", "/profile/edit/{username}", "/review", "/review/{id}", "/review/delete/{id}", "/users/{searchParam}.json"
                )
                .authenticated()
        ;
        return http.build();
    }

}
