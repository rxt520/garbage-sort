package com.smxy.rxt;

import com.smxy.rxt.common.config.StartupRunner;
import com.smxy.rxt.common.config.TaskRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.smxy.rxt.sys.mapper")
public class GarbageSortApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarbageSortApplication.class, args);
    }

    @Bean
    public StartupRunner startupRunner(){
        return new StartupRunner();
    }

    @Bean
    public TaskRunner taskRunner(){
        return new TaskRunner();
    }

}
