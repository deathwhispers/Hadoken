package pers.guangjian.hadoken.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: yanggj
 * @Date: 2022/03/04 17:08
 * @Version: 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"pers.guangjian.hadoken"})
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class);
    }
}
