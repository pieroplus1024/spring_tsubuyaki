package com.example.demo;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//	@Autowired
//	private UserDetailsService userDetailsService;

    // パスワードの暗号化
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private DataSource dataSource;

	private static final String USER_SQL = "SELECT"
			+ " user_name,"
			+ " password,"
			+ " enabled"
			+ " FROM m_user"
			+ " WHERE user_name = ?";

	private static final String ROLE_SQL = "SELECT"
			+ " user_name,"
			+ " role"
			+ " FROM m_user"
			+ " WHERE user_name = ?";

	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/webjars/∗∗", "/css/∗∗");
	}

	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/webjars/**").permitAll()		// webjarsへアクセス許可
			.antMatchers("/css/**").permitAll()			// cssへアクセス許可
			.antMatchers("/login").permitAll()			// ログインページは直リンクOK
			.antMatchers("/signup").permitAll()			// ユーザー登録画面は直リンクOK
			.antMatchers("/h2-console/**").permitAll()	// H2コンソール
			.anyRequest().authenticated();				// それ以外は直リンク禁止

		http.formLogin()
			.loginProcessingUrl("/login")		// ログイン処理のパス（th:action="@{/login}"と一致）
			.loginPage("/login")				// デフォルトではなくオリジナルのログインページの指定
			.usernameParameter("username")		// ログインページのユーザ名
			.passwordParameter("password")		// ログインページのパスワード
//			.successForwardUrl("/home")			// ログイン成功後の遷移先（@PostMapping("/home")）へ）
			.defaultSuccessUrl("/home", true)	// ログイン成功時の遷移先（@GetMapping("/home")へ）
			.failureUrl("/login")				// ログイン失敗時の遷移先（@GetMapping("/login")へ）
//			.failureForwardUrl("/login")
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login");


		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(USER_SQL)
			.authoritiesByUsernameQuery(ROLE_SQL)
			.passwordEncoder(passwordEncoder());
	}
//	@Autowired
//	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception{
//		System.out.println(userDetailsService);
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

//	}
}
