package org.zot.chai.vaadindemo.config;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.zot.chai.vaadindemo.view.LoginView;

import java.util.Set;

@Configuration
class SecurityConfiguration extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    UserDetailsManager userDetailsManager() {
        var users = Set.of("java", "scala", "kotlin")
                .stream()
                .map(name -> User.withDefaultPasswordEncoder()
                        .username(name)
                        .password("pw")
                        .roles("USER")
                        .build())
                .toList();
        return new InMemoryUserDetailsManager(users);
    }

}
