package education;

import com.datastax.driver.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfig {

    public static final Integer MAX_ALLOWED_PAGE_SIZE = 1000;

    @Bean
    public Session session() {
        Cluster.Builder builder = Cluster.builder()
                .withMaxSchemaAgreementWaitSeconds(30)
                .withQueryOptions(new QueryOptions().setFetchSize(MAX_ALLOWED_PAGE_SIZE))
                .addContactPoints("localhost");

            builder.withAuthProvider(new PlainTextAuthProvider("sphere", "NJM7znTQtA7M"));

        Cluster cluster = builder.build();
        QueryLogger queryLogger = QueryLogger.builder().build();
        cluster.register(queryLogger);
        return cluster.connect("prodb");
    }
}