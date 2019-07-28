package cn.springcloud.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;

import cn.springcloud.book.filter.ErrorFilter;
import cn.springcloud.book.filter.FirstPreFilter;
import cn.springcloud.book.filter.PostFilter;
import cn.springcloud.book.filter.SecondPreFilter;
import cn.springcloud.book.filter.ThirdPreFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
    
    /**
     * Groovy加载方法配置，20秒自动刷新
     */
    @Component
    public static class GroovyRunner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            MonitoringHelper.initMocks();
            FilterLoader.getInstance().setCompiler(new GroovyCompiler());
            try {
                //FIXME 改名目录可以固定一个文件目录，通过配置，该配置有默认值；for exmaple : /Data/filters ...
                //FIXME 从filterloader中获取filter，并可以提供一个获取出口，该regster中心； 客户端使用时从该处获取；只要通过key就可以了；

                FilterFileManager.setFilenameFilter(new GroovyFileFilter());
                FilterFileManager.init(20, "/Users/Administrator/workspace-scbook/ch8-1/ch8-1-zuul-server/src/main/java/cn/springcloud/book/groovy");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
//    @Bean
//    public FirstPreFilter firstPreFilter(){
//    	return new FirstPreFilter();
//    }
    
    @Bean
    public SecondPreFilter secondPreFilter(){
    	return new SecondPreFilter();
    }
    
    @Bean
    public ThirdPreFilter thirdPreFilter(){
    	return new ThirdPreFilter();
    }
    
//    @Bean
//    public ErrorFilter errorFilter(){
//    	return new ErrorFilter();
//    }
    
    @Bean
    public PostFilter postFilter(){
    	return new PostFilter();
    }
}
