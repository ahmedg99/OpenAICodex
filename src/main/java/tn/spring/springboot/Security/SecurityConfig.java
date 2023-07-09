package tn.spring.springboot.Security;

 import lombok.RequiredArgsConstructor;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 import org.springframework.security.config.http.SessionCreationPolicy;
 import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 import tn.spring.springboot.Security.Filter.CustomAuthenticationFilter;
 import tn.spring.springboot.Security.Filter.CustomAuthorizationFilter;

 import static org.springframework.http.HttpMethod.GET;
 import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig   extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder ;
    private final UserDetailsService userDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        //customAuthenticationFilter.setFilterProcessesUrl("/api/login/");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS) ;
        http.authorizeRequests().antMatchers(GET, "/login").permitAll() ;
        //http.authorizeRequests()
        //      .antMatchers("/api/user/**").access("hasRole('ROLE_ADMIN')").;
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("ROLE_ADMIN") ;
         http.addFilter(customAuthenticationFilter);
         http.addFilterBefore(new CustomAuthorizationFilter() , UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
     @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
