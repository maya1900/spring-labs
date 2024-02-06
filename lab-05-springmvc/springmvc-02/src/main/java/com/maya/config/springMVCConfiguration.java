package com.maya.config;

import com.maya.core.interceptor.FirstInterceptor;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class springMVCConfiguration implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public FirstInterceptor firstInterceptor() {
        logger.info("[firstInterceptor][创建 FirstInterceptor]");
        return new FirstInterceptor();
    }

    @Bean
    public FirstInterceptor secondInterceptor() {
        logger.info("[secondInterceptor][创建 SecondInterceptor]");
        return new FirstInterceptor();
    }

    @Bean
    public FirstInterceptor thirdInterceptor() {
        logger.info("[thirdInterceptor][创建 ThirdInterceptor]");
        return new FirstInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(firstInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(secondInterceptor()).addPathPatterns("/users/current_user");
        registry.addInterceptor(thirdInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public ServletRegistrationBean testServlet01() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(new HttpServlet() {

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                logger.info("[doGet][uri: {}]", req.getRequestURI());
            }
        });
        servletRegistrationBean.setUrlMappings(Collections.singleton("/test/01"));
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean testFilter01() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>(new Filter() {

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                logger.info("[doFilter][uri: {}]", ((HttpServletRequest) request).getRequestURI());
                chain.doFilter(request, response);
            }
        });
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/test/*"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<?> testListener01() {
        return new ServletListenerRegistrationBean<>(new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                logger.info("[contextInitialized]");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                logger.info("[contextDestroyed]");
            }
        });
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        // 创建 UrlBasedCorsConfigurationSource 配置源，类似 CorsRegistry 注册表
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 创建 CorsConfiguration 配置，相当于 CorsRegistration 注册信息
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("*")); // 允许所有请求来源
        config.setAllowCredentials(true); // 允许发送 Cookie
        config.addAllowedMethod("*"); // 允许所有请求 Method
        config.setAllowedHeaders(Collections.singletonList("*")); // 允许所有请求 Header
//        config.setExposedHeaders(Collections.singletonList("*")); // 允许所有响应 Header
        config.setMaxAge(1800L); // 有效期 1800 秒，2 小时
        source.registerCorsConfiguration("/**", config);
        // 创建 FilterRegistrationBean 对象
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(source)); // 创建 CorsFilter 过滤器
        bean.setOrder(0); // 设置 order 排序。这个顺序很重要哦，为避免麻烦请设置在最前
        return bean;
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        // 创建 FastJsonHttpMessageConverter 对象
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        // 自定义 FastJson 配置
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setCharset(Charset.defaultCharset()); // 设置字符集
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect); // 剔除循环引用
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        // 设置支持的 MediaType
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
//                MediaType.APPLICATION_JSON_UTF8));
//        // 添加到 converters 中
//        converters.add(0, fastJsonHttpMessageConverter); // 注意，添加到最开头，放在 MappingJackson2XmlHttpMessageConverter 前面
//    }
}
