package education;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static education.RouteUri.directExchangeUri;

/**
 * Created by yaroslav on 08.01.17.
 */
@SpringBootApplication(exclude = CamelAutoConfiguration.class)
@RestController
public class ProducerApp{
    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    private ProducerTemplate sender;
    private String uri;

    @RequestMapping("send/{text}")
    public void send(@PathVariable String text){
        sender.sendBody(uri, new Message(text));
    }

    @Autowired
    CassandraService cassandraService;

    @RequestMapping("cassandra")
    public void xassandraTest(){
        cassandraService.simpleSelect();
    }

    @Autowired
    public ProducerApp(CamelConfig.CamelContextHolder camelContextHolder){
        sender = camelContextHolder.getCamelContext().createProducerTemplate();
        uri = directExchangeUri();
    }
}
