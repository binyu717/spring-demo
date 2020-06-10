package cn.yu.demo.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring demo application
 *
 * @author bin.yu
 * @create 2020-03-31 11:30
 * @info best wishes no bug
 **/
@SpringBootApplication(scanBasePackages = {"cn.yu.demo"})
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }
}
