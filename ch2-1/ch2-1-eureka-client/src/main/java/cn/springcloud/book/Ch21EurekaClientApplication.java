package cn.springcloud.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * FIXME 1，@EnableDiscoveryClient注解是基于spring-cloud-commons依赖，并且在classpath中实现；
 *
 * FIXME 2，@EnableEurekaClient注解是基于spring-cloud-netflix依赖，只能为eureka作用；
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
public class Ch21EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ch21EurekaClientApplication.class, args);
	}
}
