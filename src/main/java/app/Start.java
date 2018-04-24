package app;

import connector.IConnectMq;
import connector.KafkaMqConnector;
import connector.MqConnectorFactory;
import connector.RabbitMqConnector;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import pojo.Data;
import pojo.MqConfig;
import request.Event;
import request.MicroServiceCommonRequest;
import request.Queue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        //Init Vertx
        Vertx vertx = Vertx.vertx();

        //Read config
        Buffer buffer = vertx.fileSystem().readFileBlocking("src/main/resources/mq-config.json");
        MqConfig mqConfig = Json.decodeValue(buffer, MqConfig.class);

        //Prepare data to send
        List<String> cardNumbers = new ArrayList<>();
        cardNumbers.add("970411112222");
        cardNumbers.add("970411223344");
        cardNumbers.add("405211119999");
        Data data = new Data()
                .setAccountNumber("225562366")
                .setBalance(BigInteger.valueOf(10000000))
                .setCardNumbers(cardNumbers);

        //Build request
        MicroServiceCommonRequest request = new MicroServiceCommonRequest.CommRequestBuilder()
                .setEvent(Event.SAVE.getCode())
                .setPayload(data)
                .setQueue(Queue.AccountQueue)
                .build();

        //Create rabbit and kafka connector
        IConnectMq rabbit = MqConnectorFactory.getConnector(new RabbitMqConnector(mqConfig, vertx));
        IConnectMq kafka = MqConnectorFactory.getConnector(new KafkaMqConnector(mqConfig, vertx));

        //Declare queue
        rabbit.declareQueue();

        //Publish message
        rabbit.publish(request);
        kafka.publish(request);
    }

}
