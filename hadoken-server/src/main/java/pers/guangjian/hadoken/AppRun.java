package pers.guangjian.hadoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author yanggj
 * @date 2022/03/04 17:08
 * @version 1.0.0
 */
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class);
    }

}
