package springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	@Autowired
	MyUserDetailsService userDetailService;
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userDetailService);	
		 auth.inMemoryAuthentication().withUser("admin").password("p").roles("ADMIN");
	}
	@Override
	public void configure(HttpSecurity http) throws Exception{
//		super.configure(http);
		http.csrf().disable().authorizeRequests()
			.antMatchers("/view").hasRole("admin")
			.antMatchers("/user").hasAnyRole("admin","user")
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and().formLogin();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	
}
