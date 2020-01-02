package com.jung.channel.bootstrap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableAsync
@EnableWebMvc
@ComponentScan("com.jung.channel.*")
@EnableTransactionManagement
@EnableSwagger2
@EnableHystrix
@EnableConfigurationProperties
@EnableAutoConfiguration
public class ChannelApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChannelApiApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean initHttpLoggerFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}
