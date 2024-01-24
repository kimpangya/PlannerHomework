package com.sparta.plannerHomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PlannerHomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlannerHomeworkApplication.class, args);
    }

}
