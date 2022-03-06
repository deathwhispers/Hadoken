package pers.guangjian.hadoken.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author: yanggj
 * @Date: 2022/03/04 17:08
 * @Version: 1.0.0
 */
@EntityScan("pers.guangjian.*.*")
@EnableJpaRepositories(basePackages = "pers.guangjian.*")
@ComponentScan(basePackages = "pers.guangjian")
@SpringBootApplication(scanBasePackages = {"pers.guangjian.hadoken"})
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class);
    }

}
