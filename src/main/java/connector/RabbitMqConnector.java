package connector;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.rabbitmq.RabbitMQOptions;
import pojo.MqConfig;
import request.MicroServiceCommonRequest;
import request.Queue;

public class RabbitMqConnector implements IConnectMq {

    private RabbitMQClient client;
    private MqConfig mqConfig;
    private Vertx vertx;

    public RabbitMqConnector(MqConfig mqConfig, Vertx vertx) {
        this.mqConfig = mqConfig;
        this.vertx = vertx;
    }

    @Override
    public IConnectMq initConnection() {
        RabbitMQOptions config = new RabbitMQOptions();
        config.setUser(mqConfig.getUser());
        config.setPassword(mqConfig.getPassword());
        config.setHost(mqConfig.getHost());
        config.setPort(mqConfig.getPort());
        config.setVirtualHost(mqConfig.getVirtualHost());
        config.setConnectionTimeout(mqConfig.getConnectionTimeout()); // in milliseconds
        config.setRequestedHeartbeat(mqConfig.getRequestedHeartbeat()); // in seconds
        config.setHandshakeTimeout(mqConfig.getHandshakeTimeout()); // in milliseconds
        config.setRequestedChannelMax(mqConfig.getRequestedChannelMax());
        config.setNetworkRecoveryInterval(mqConfig.getNetworkRecoveryInterval()); // in milliseconds
        config.setAutomaticRecoveryEnabled(mqConfig.getAutomaticRecoveryEnabled());
        client = RabbitMQClient.create(vertx, config);
        return this;
    }

    @Override
    public IConnectMq declareQueue() {
        JsonObject config = new JsonObject();
        config.put("x-message-ttl", 10_000L);

        client.queueDeclare(Queue.AccountQueue, true, false, true, config, queueResult -> {
            if (queueResult.succeeded()) {
                System.out.println("Queue declared!");
            } else {
                System.err.println("Queue failed to be declared!");
                queueResult.cause().printStackTrace();
            }
        });
        return this;
    }

    @Override
    public IConnectMq publish(MicroServiceCommonRequest request) {
        JsonObject message = new JsonObject().put("body", new JsonObject(request.getPayload()));
        client.basicPublish("", request.getQueue(), message, pubResult -> {
            System.out.println("Send with rabbit mq " + message.encodePrettily());
            if (pubResult.succeeded()) {
                System.out.println("Message published !");
            } else {
                pubResult.cause().printStackTrace();
            }
        });
        return this;
    }
}
