package education;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * Created by yaroslav on 08.01.17.
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Message {
    final String text;
}
