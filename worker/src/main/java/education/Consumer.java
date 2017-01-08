package education;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static education.RouteUri.directExchangeUri;


/**
 * Created by yaroslav on 08.01.17.
 */
@Component
public class Consumer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        String uri = directExchangeUri();
        from(uri).bean(this, "process");
    }

    public void process(Message message) {
        System.out.println(message);
    }
}
