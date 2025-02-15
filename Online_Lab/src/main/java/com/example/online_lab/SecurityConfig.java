package com.example.online_lab;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // 禁用 CSRF 防護
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 啟用 CORS
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()  // 允許所有請求
                )
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 加入 JWT 認證過濾器
        return http.build();
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8081"); // 允許前端 localhost:5173 發送請求
        config.addAllowedMethod("*"); // 允許所有 HTTP 方法
        config.addAllowedHeader("*"); // 允許所有標頭
        config.setAllowCredentials(true); // 允許攜帶 Cookie
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}




