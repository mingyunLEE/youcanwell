package nom.youcanwell.security;

import lombok.RequiredArgsConstructor;
import nom.youcanwell.security.filter.MemberAuthenticationEntryPoint;
import nom.youcanwell.security.handler.MemberAccessDeniedHandler;
import nom.youcanwell.security.service.OauthService;
import nom.youcanwell.security.utils.CustomAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final OauthService oauthservice;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .formLogin().disable()
                .httpBasic().disable()

                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .authorizeHttpRequests(authorize -> authorize
//                        .antMatchers(HttpMethod.POST, "/*/questions/**").hasAnyRole("ADMIN","USER")
//                        .antMatchers(HttpMethod.PATCH, "/*/questions/**").hasAnyRole("ADMIN","USER")
//                        .antMatchers(HttpMethod.DELETE, "/*/questions/**").hasAnyRole("ADMIN","USER")
//                        .antMatchers(HttpMethod.POST, "/*/user/**").hasAnyRole("ADMIN","USER")
//                        .antMatchers(HttpMethod.PATCH, "/*/user/**").hasAnyRole("ADMIN","USER")
//                        .antMatchers(HttpMethod.DELETE, "/*/user/**").hasAnyRole("ADMIN","USER")
//                        .antMatchers(HttpMethod.POST, "/*/answer/**").hasAnyRole("ADMIN","USER")
//                        .antMatchers(HttpMethod.PATCH, "/*/answer/**").hasAnyRole("ADMIN","USER")
//                        .antMatchers(HttpMethod.DELETE, "/*/answer/**").hasAnyRole("ADMIN","USER")
                                .anyRequest().permitAll()
                )
                .oauth2Login()
                .defaultSuccessUrl("/")
                .failureUrl("/login") //로그인 실패시 이동해야하는 위치
                .userInfoEndpoint() //로그인 성공후 사용자정보를 가져오겠다.
                .userService(oauthservice);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
        //모든 출처(Origin)에 대해 스크립트 기반의 HTTP 통신을 허용하고 추가적으로 운영 서버 환경에서 요구사항에 맞게 변경이 가능
        configuration.setAllowedOrigins(Arrays.asList("*"));
        //파라미터로 지정한 HTTP Method에 대한 HTTP 통신을 허용
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Accept","X-Requested-With","Content-Type","Access-Control-Request-Method",
                "Access-Control-Request-Headers","Authorization","Refresh","Connection","Content","Host",
                "Referer","Access-Control-Allow-Origin"));
        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("Refresh");
        configuration.setMaxAge(4600l);
        //터페이스의 구현 클래스인 UrlBasedCorsConfigurationSource 클래스의 객체를 생성

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        //모든 URL에 해당사항 적용하겠다.
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
