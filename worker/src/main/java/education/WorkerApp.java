package education;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by yaroslav on 08.01.17.
 */
@SpringBootApplication(exclude = CamelAutoConfiguration.class)
public class WorkerApp implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(WorkerApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
