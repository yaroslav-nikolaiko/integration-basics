package education;

/**
 * Created by yaroslav on 08.01.17.
 */
public class RouteUri {
    public static String directExchangeUri() {
        StringBuilder builder = new StringBuilder("rabbitmq://");
        builder.append("localhost");
        builder.append(":");
        builder.append(5672);
        builder.append("/");
        builder.append("test-exchange");
        builder.append("?");
        builder.append("routingKey=default&");
        builder.append("queue=simple-test");
        return builder.toString();
    }
}
