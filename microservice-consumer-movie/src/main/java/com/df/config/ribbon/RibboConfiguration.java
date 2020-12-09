package com.df.config.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ribbon配置类  该类不能在程序上下文@componentscan中
 * 否则会被所有的ribbon client共享
 * 可以利用@ComponentScan排除相应的类或者包  不扫描
 * @author hanyli
 * @date 2020/12/9
 */
@Configuration
public class RibboConfiguration {
    @Bean
    public IRule ribbonrule() {
        return new RandomRule();
    }
}
