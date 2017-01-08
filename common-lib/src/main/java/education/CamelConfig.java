package education;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConversionException;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.support.TypeConverterSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Import({CamelAutoConfiguration.class})
public class CamelConfig {

    @Bean
    CamelContextHolder camelContextHolder(CamelContext camelContext, CamelMapper camelMapper) {
        return new CamelContextHolder(camelContext, camelMapper);
    }

    @Bean
    CamelMapper camelMapper(ObjectMapper mapper) {
        return new CamelMapper(mapper);
    }

    public static class CamelContextHolder {
        @Getter
        private CamelContext camelContext;

        private CamelContextHolder(final CamelContext camelContext, final CamelMapper camelMapper) {
            Set<Class<?>> convertables = new HashSet<>();
            convertables.add(Message.class);
            convertables.forEach(clazz -> {
                camelContext.getTypeConverterRegistry().addTypeConverter(clazz, byte[].class, camelMapper);
                camelContext.getTypeConverterRegistry().addTypeConverter(byte[].class, clazz, camelMapper);
            });
            this.camelContext = camelContext;
        }
    }

    public static class CamelMapper extends TypeConverterSupport {
        private final ObjectMapper mapper;

        public CamelMapper(final ObjectMapper mapper) {
            this.mapper = mapper;
        }

        @Override
        public <T> T convertTo(final Class<T> type, final Exchange exchange,
                               final Object value) throws TypeConversionException {
            try {
                if (value.getClass() == byte[].class) {
                    return mapper.readValue((byte[]) value, type);
                } else {
                    return (T) mapper.writeValueAsBytes(value);
                }
            } catch (IOException e) {
                throw new TypeConversionException(value, type, e);
            }
        }
    }
}
