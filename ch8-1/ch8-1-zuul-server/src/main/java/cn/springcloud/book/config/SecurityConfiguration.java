package cn.springcloud.book.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//FIXME 权限配置，所有的权限都可以执行；
		http
		.requestMatcher(EndpointRequest.toAnyEndpoint())
		.authorizeRequests()
		.anyRequest()
		.permitAll();
	}
}